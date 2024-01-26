package com.nkrivenko.upgate.upgateintegration.mappers

import com.nkrivenko.upgate.upgateintegration.domain.Payment
import com.nkrivenko.upgate.upgateintegration.domain.TransactionStatus
import com.nkrivenko.upgate.upgateintegration.dto.PaymentRequest
import com.nkrivenko.upgate.upgateintegration.dto.Product
import com.nkrivenko.upgate.upgateintegration.dto.ProductInfo
import com.nkrivenko.upgate.upgateintegration.dto.SaleRequest
import java.util.UUID

fun PaymentRequest.toSaleRequest(paymentId: String, successUrl: String, failureUrl: String): SaleRequest = SaleRequest(
    paymentMethod,
    paymentId,
    merchantCustomerId,
    amount,
    currencyCode,
    email,
    language,
    countryCode,
    true,
    successUrl,
    failureUrl,
    shopName ?: "",
    shopUrl ?: "",
    products.map { it.toProduct() },
)

fun ProductInfo.toProduct(): Product = Product(
    merchantProductId, productType, productPrice, productName, productDescription
)

fun PaymentRequest.toPayment(): Payment = Payment(
    null, amount, currencyCode, countryCode, merchantCustomerId, TransactionStatus.CREATED
)
