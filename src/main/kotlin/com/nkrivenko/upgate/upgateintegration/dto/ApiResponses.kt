package com.nkrivenko.upgate.upgateintegration.dto

data class PaymentResponse(
    val redirectUrl: String
)

data class ErrorResponse(
    val code: Int?,
    val message: String
)
