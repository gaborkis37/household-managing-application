package com.household.householdmanager.dto

import com.household.householdmanager.controller.auth.model.SignUpRequest
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class SignUpRequestDTO(
    @field:NotEmpty(message = "First name is required.")
    @field:Size(min = 2, max = 100, message = "The first name must be between 2 and 100 characters.")
    val firstName: String,
    @field:NotEmpty(message = "Last name is required.")
    @field:Size(min = 2, max = 100, message = "The last name must be between 2 and 100 characters.")
    val lastName: String,
    @field:NotEmpty(message = "Email is required")
    @field:Email
    val email: String,
    @field:NotEmpty(message = "Password is required")
    val password: String,
    val image: String
) {
    fun toSignUpRequest(): SignUpRequest {
        return SignUpRequest(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            image = image
        )
    }
}