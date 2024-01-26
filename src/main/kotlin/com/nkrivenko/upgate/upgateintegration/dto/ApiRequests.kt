package com.nkrivenko.upgate.upgateintegration.dto

import java.math.BigDecimal
import java.util.UUID

data class PaymentRequest(
    val paymentMethod: String,
    val merchantCustomerId: String,
    val amount: BigDecimal,
    val currencyCode: String,
    val email: String,
    val language: String,
    val countryCode: String,
    val products: List<ProductInfo>,
    val shopName: String? = null,
    val shopUrl: String? = null,
    val merchantPaymentId: String = UUID.randomUUID().toString(),
)

data class ProductInfo(
    val merchantProductId: String,
    val productId: String,
    val productType: String,
    val productPrice: String,
    val productName: String,
    val productDescription: String
)
