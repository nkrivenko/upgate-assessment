package com.nkrivenko.upgate.upgateintegration.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal

@JsonNaming(SnakeCaseStrategy::class)
data class SaleRequest(
    val paymentMethod: String,
    val merchantPaymentId: String,
    val merchantCustomerId: String,
    val amount: BigDecimal,
    val currencyCode: String,
    val email: String,
    val language: String,
    val countryCode: String,
    val forced3d: Boolean,
    val successUrl: String,
    val failureUrl: String,
    val shopName: String,
    val shopUrl: String,
    val products: List<Product>
)

@JsonNaming(SnakeCaseStrategy::class)
data class PaymentCallbackRequest(
    val type: String?,
    val data: TransactionData,
)

@JsonNaming(SnakeCaseStrategy::class)
data class TransactionData(
    val transactionId: String,
    val transactionType: String,
    val transactionStatus: TransactionStatus,
    val createdAt: String,
    val responseCode: Int,
    val responseText: String,
    val transactionDetails: TransactionDetails,
    val paymentContext: PaymentContext,
    val paymentDetails: PaymentDetails,
    val payment: PaymentData
)

@JsonNaming(SnakeCaseStrategy::class)
data class TransactionDetails(
    val processorMerchantAccountId: String,
    val processorTransactionId: String,
    val processor: String,
    val processorResponseCode: String
)

@JsonNaming(SnakeCaseStrategy::class)
data class PaymentContext(
    val browserUserAgent: String,
    val browserScreenHeight: String,
    val browserColorDepth: String,
    val browserLanguage: String,
    val browserTimezoneOffset: String,
    val browserScreenWidth: String,
    val challengeWindowSize: String,
    val browserHasJsEnabled: String,
    val browserHasJavaEnabled: String,
    val browserAcceptHeader: String,
    val ip: String
)

@JsonNaming(SnakeCaseStrategy::class)
data class PaymentDetails(
    val cardScheme: String,
    val cardTokenId: String,
    val cardBin: String,
    val cardLastFourDigits: String,
    val customerFullName: String,
    val cardExpiryMonth: String,
    val cardExpiryYear: String,
    val paymentTokenId: String?
)

enum class TransactionStatus {
    SUCCESS,
    DECLINE,
    ERROR
}
