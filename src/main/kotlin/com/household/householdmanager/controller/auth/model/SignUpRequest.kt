package com.household.householdmanager.controller.auth.model

data class SignUpRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val image: String
)