package com.nkrivenko.upgate.upgateintegration.controllers

import com.nkrivenko.upgate.upgateintegration.dto.PaymentCallbackRequest
import com.nkrivenko.upgate.upgateintegration.services.PaymentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WebhookSaleController(private val paymentService: PaymentService) {
    @PostMapping("/callback")
    suspend fun handleCallback(@RequestBody payload: PaymentCallbackRequest) {
        paymentService.handlePaymentCallback(payload)
    }
}