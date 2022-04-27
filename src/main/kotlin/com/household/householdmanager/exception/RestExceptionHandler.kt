package com.household.householdmanager.exception

import com.household.householdmanager.exception.model.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ProductNotFoundException::class, HouseholdNotFoundException::class])
    fun handleNotFoundExceptions(ex: NotFoundException): ResponseEntity<ApiError> {
        val apiError = ApiError(httpStatus = HttpStatus.NOT_FOUND, msg = ex.message)

        return buildResponseEntity(apiError)
    }

    private fun buildResponseEntity(apiError: ApiError): ResponseEntity<ApiError> {
        return ResponseEntity<ApiError>(apiError, apiError.httpStatus)
    }
}