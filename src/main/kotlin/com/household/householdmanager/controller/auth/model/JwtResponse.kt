package com.household.householdmanager.controller.auth.model

data class JwtResponse(
    val jwt: String,
    val email: String,
    val userName: String,
    val roles: MutableList<String>
)