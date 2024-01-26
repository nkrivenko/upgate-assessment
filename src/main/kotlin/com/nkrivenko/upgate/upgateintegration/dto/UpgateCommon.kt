package com.nkrivenko.upgate.upgateintegration.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Product(
    val merchantProductId: String,
    val productType: String,
    val productPrice: String,
    val productName: String,
    val productDescription: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PaymentData(
    val paymentId: String,
    val paymentType: String,
    val paymentMethod: String,
    val createdAt: String,
    val merchantId: String,
    val merchantPaymentId: String,
    val merchantCustomerId: String,
    val email: String,
    val amount: String,
    val language: String?,
    val countryCode: String,
    val currencyCode: String,
    val baseAmount: String?,
    val baseCurrencyCode: String?,
    val forced3d: Boolean?,
    val successUrl: String,
    val failureUrl: String,
    val shopName: String?,
    val shopUrl: String?,
    val session: PaymentSession?,
    val products: List<Product>
)
