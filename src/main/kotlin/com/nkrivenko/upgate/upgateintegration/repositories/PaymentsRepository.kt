package com.nkrivenko.upgate.upgateintegration.repositories

import com.nkrivenko.upgate.upgateintegration.domain.Payment
import com.nkrivenko.upgate.upgateintegration.domain.TransactionStatus
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface PaymentsRepository : CoroutineCrudRepository<Payment, String> {
    @Modifying
    @Query("UPDATE payment SET transaction_status = :txStatus WHERE id = :id")
    suspend fun updatePaymentStatus(id: String, transactionStatus: TransactionStatus)
}
