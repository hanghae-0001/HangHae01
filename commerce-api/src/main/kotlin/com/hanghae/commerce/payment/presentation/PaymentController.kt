package com.hanghae.commerce.payment.presentation

import com.hanghae.commerce.payment.application.PaymentFacade
import com.hanghae.commerce.payment.domain.command.PaymentCommand
import com.hanghae.commerce.payment.presentation.dto.PaymentRequest
import com.hanghae.commerce.payment.presentation.dto.PaymentResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/payments")
class PaymentController(
    private val paymentFacade: PaymentFacade,
) {

    @PostMapping("")
    fun payment(
        @RequestBody @Valid
        paymentRequest: PaymentRequest,
    ): ResponseEntity<PaymentResponse> {
        return ResponseEntity.ok(
            PaymentResponse(
                paymentFacade.payment(
                    PaymentCommand(
                        paymentRequest.orderId,
                        paymentRequest.bankAccount,
                    ),
                ),
            ),
        )
    }
}
