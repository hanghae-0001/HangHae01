package com.hanghae.commerce.payment.presentation

import com.hanghae.commerce.payment.application.PaymentFacade
import com.hanghae.commerce.payment.presentation.dto.PaymentRequest
import com.hanghae.commerce.payment.presentation.dto.PaymentResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class PaymentController(
    private val paymentFacade: PaymentFacade
) {

    @PostMapping("")
    fun payment(paymentRequest: PaymentRequest): ResponseEntity<PaymentResponse> {
        return ResponseEntity.ok(
            paymentFacade.payment(paymentRequest)
        )
    }
}
