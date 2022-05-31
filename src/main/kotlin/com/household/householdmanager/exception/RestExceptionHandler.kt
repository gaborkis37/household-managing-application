package com.household.householdmanager.exception

import com.household.householdmanager.exception.model.ApiError
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors

@ControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ProductNotFoundException::class, HouseholdNotFoundException::class])
    fun handleNotFoundExceptions(ex: NotFoundException): ResponseEntity<ApiError> {
        val apiError = ApiError(httpStatus = HttpStatus.NOT_FOUND, msg = ex.message)

        return buildResponseEntity(apiError)
    }

    @ExceptionHandler(value = [EmailAlreadyInUseException::class])
    fun handleBadRequestExceptions(ex: BadRequestException): ResponseEntity<ApiError> {
        val apiError = ApiError(httpStatus = HttpStatus.BAD_REQUEST, msg = ex.message)

        return buildResponseEntity(apiError)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val body: HashMap<String, List<String?>> = hashMapOf();

        val errors = ex.bindingResult
            .fieldErrors
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList())
            .toList();


        body.put("errors", errors);

        return ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    private fun buildResponseEntity(apiError: ApiError): ResponseEntity<ApiError> {
        return ResponseEntity<ApiError>(apiError, apiError.httpStatus)
    }
}