package com.nkrivenko.upgate.upgateintegration.controllers

import com.nkrivenko.upgate.upgateintegration.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpServerErrorException

@RestControllerAdvice
class WebErrorHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.OK)
    fun handleBadGateway(ex: HttpServerErrorException): ErrorResponse {
        return ErrorResponse(HttpStatus.BAD_GATEWAY.value(), "Upgate not responding")
    }

}