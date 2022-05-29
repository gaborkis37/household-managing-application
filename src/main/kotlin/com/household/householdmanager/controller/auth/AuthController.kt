package com.household.householdmanager.controller.auth

import com.household.householdmanager.controller.auth.model.JwtResponse
import com.household.householdmanager.controller.auth.model.LoginRequest
import com.household.householdmanager.controller.auth.model.SignUpRequest
import com.household.householdmanager.model.ERole
import com.household.householdmanager.model.Role
import com.household.householdmanager.model.User
import com.household.householdmanager.model.UserDetailsImpl
import com.household.householdmanager.repository.RoleRepository
import com.household.householdmanager.repository.UserRepository
import com.household.householdmanager.security.jwt.JwtTokenUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val jwtTokenUtil: JwtTokenUtil
) {

    //TODO: Delegate all auth logic to AuthenticationService

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.email,loginRequest.password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtTokenUtil.generateJwtToken(authentication)

        val userDetails = authentication.principal as UserDetailsImpl

        val roles = userDetails.authorities.stream()
            .map { item -> item.authority }
            .collect(Collectors.toList())

        return ResponseEntity.ok(
            JwtResponse(
                jwt,
                userDetails.email,
                userDetails.username,
                roles
            )
        )
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Any> {
        if(userRepository.existsByEmail(signUpRequest.email)) {
            return ResponseEntity.badRequest().body("Error: The email is already in use.")
        }

        val user = User(
            firstName = signUpRequest.firstName,
            lastName = signUpRequest.lastName,
            email = signUpRequest.email,
            passWord = encoder.encode(signUpRequest.password),
            image = signUpRequest.image,
        )

        val roles = mutableSetOf<Role>()
        val userRole = roleRepository.findByName(ERole.USER)
        roles.add(userRole)

        user.roles = roles

        val savedUser = userRepository.save(user)

        return ResponseEntity.ok(savedUser)
    }
}