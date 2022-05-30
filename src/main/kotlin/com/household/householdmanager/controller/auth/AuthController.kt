package com.household.householdmanager.controller.auth

import com.household.householdmanager.controller.auth.model.JwtResponse
import com.household.householdmanager.controller.auth.model.LoginRequest
import com.household.householdmanager.controller.auth.model.SignUpRequest
import com.household.householdmanager.model.User
import com.household.householdmanager.service.AuthenticationService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): JwtResponse {
        return authenticationService.authenticateUser(loginRequest)
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: SignUpRequest): User {
        return authenticationService.registerUser(signUpRequest)
    }
}