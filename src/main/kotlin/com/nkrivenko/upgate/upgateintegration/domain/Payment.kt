package com.nkrivenko.upgate.upgateintegration.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.Instant

data class Payment(
    @Id var id: String?,
    var amount: BigDecimal,
    var currencyCode: String,
    var countryCode: String,
    var customerId: String,
    var transactionStatus: TransactionStatus,
) {
    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var updatedAt: Instant
}
