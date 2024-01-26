package com.nkrivenko.upgate.upgateintegration.services

import com.nkrivenko.upgate.upgateintegration.configuration.properties.UpgateProperties
import com.nkrivenko.upgate.upgateintegration.domain.TransactionStatus
import com.nkrivenko.upgate.upgateintegration.dto.PaymentCallbackRequest
import com.nkrivenko.upgate.upgateintegration.dto.PaymentRequest
import com.nkrivenko.upgate.upgateintegration.dto.PaymentResponse
import com.nkrivenko.upgate.upgateintegration.dto.SaleResponse
import com.nkrivenko.upgate.upgateintegration.integration.UpgateClient
import com.nkrivenko.upgate.upgateintegration.mappers.toPayment
import com.nkrivenko.upgate.upgateintegration.mappers.toSaleRequest
import com.nkrivenko.upgate.upgateintegration.repositories.PaymentsRepository
import org.springframework.stereotype.Service
import java.util.UUID

interface PaymentService {
    suspend fun registerPayment(request: PaymentRequest): PaymentResponse?
    suspend fun handlePaymentCallback(request: PaymentCallbackRequest)
}

@Service
class PaymentServiceImpl(
    private val upgateClient: UpgateClient,
    private val upgateProperties: UpgateProperties,
    private val paymentsRepository: PaymentsRepository,
) : PaymentService {

    override suspend fun registerPayment(request: PaymentRequest): PaymentResponse? {
        val payment = paymentsRepository.save(request.toPayment())
        val id = payment.id!!
        val upgateRequest = request.toSaleRequest(id, upgateProperties.successUrl, upgateProperties.failureUrl)

        return try {
            upgateClient.sale(upgateRequest).data.session?.redirectUrl?.let { PaymentResponse(it) }
        } catch (e: Exception) {
            paymentsRepository.updatePaymentStatus(id, TransactionStatus.ERROR)
            null
        }
    }

    override suspend fun handlePaymentCallback(request: PaymentCallbackRequest) {
        paymentsRepository.updatePaymentStatus(
            request.data.payment.merchantPaymentId,
            when(request.data.transactionStatus) {
                com.nkrivenko.upgate.upgateintegration.dto.TransactionStatus.SUCCESS -> TransactionStatus.SUCCEEDED
                com.nkrivenko.upgate.upgateintegration.dto.TransactionStatus.DECLINE -> TransactionStatus.FAILED
                else -> TransactionStatus.ERROR
            }
        )
    }

}
