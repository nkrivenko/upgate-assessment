package com.nkrivenko.upgate.upgateintegration.controllers

import com.nkrivenko.upgate.upgateintegration.dto.PaymentRequest
import com.nkrivenko.upgate.upgateintegration.dto.PaymentResponse
import com.nkrivenko.upgate.upgateintegration.dto.SaleResponse
import com.nkrivenko.upgate.upgateintegration.services.PaymentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/payments")
class PaymentsController(private val paymentService: PaymentService) {

    @PostMapping
    suspend fun registerPayment(@RequestBody request: PaymentRequest): PaymentResponse?
        = paymentService.registerPayment(request)

}