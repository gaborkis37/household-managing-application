package com.household.householdmanager.service

import com.household.householdmanager.controller.auth.model.JwtResponse
import com.household.householdmanager.controller.auth.model.LoginRequest
import com.household.householdmanager.controller.auth.model.SignUpRequest
import com.household.householdmanager.exception.EmailAlreadyInUseException
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
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val jwtTokenUtil: JwtTokenUtil
) {

    fun authenticateUser(loginRequest: LoginRequest): JwtResponse {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.email,loginRequest.password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtTokenUtil.generateJwtToken(authentication)

        val userDetails = authentication.principal as UserDetailsImpl

        val roles = userDetails.authorities.stream()
            .map { item -> item.authority }
            .collect(Collectors.toList())

        return JwtResponse(
            jwt,
            userDetails.email,
            userDetails.username,
            roles
        )
    }

    fun registerUser(signUpRequest: SignUpRequest): User {
        checkIfEmailAlreadyInUse(signUpRequest.email)

        val user = User(
            firstName = signUpRequest.firstName,
            lastName = signUpRequest.lastName,
            email = signUpRequest.email,
            passWord = encoder.encode(signUpRequest.password),
            image = signUpRequest.image,
            roles = setOf(roleRepository.findByName(ERole.USER))
        )

        return userRepository.save(user)
    }

    private fun checkIfEmailAlreadyInUse(email: String) {
        if(userRepository.existsByEmail(email)) {
            throw EmailAlreadyInUseException("Email already in use.")
        }
    }
}