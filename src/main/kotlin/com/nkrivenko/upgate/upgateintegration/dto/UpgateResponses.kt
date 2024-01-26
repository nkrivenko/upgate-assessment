package com.nkrivenko.upgate.upgateintegration.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class SaleResponse(
    val type: String,
    val data: PaymentData
)

@JsonNaming(SnakeCaseStrategy::class)
data class PaymentSession(
    val createdAt: String,
    val expiresAt: String,
    val redirectUrl: String
)
