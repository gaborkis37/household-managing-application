package com.household.householdmanager.dto

import com.household.householdmanager.controller.auth.model.LoginRequest
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty


data class LoginRequestDTO(
    @field:NotEmpty(message = "Email is required.")
    @field:NotBlank
    @field:Email
    val email: String,
    @field:NotEmpty(message = "Password is required.")
    @field:NotBlank
    val password: String
) {

    fun toLoginRequest(): LoginRequest {
        return LoginRequest(
            email = email,
            password = password
        )
    }

}