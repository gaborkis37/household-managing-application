package com.household.householdmanager.exception.model

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ApiError(
    val httpStatus: HttpStatus,
    val localeDate: LocalDateTime = LocalDateTime.now(),
    val msg: String?
)
