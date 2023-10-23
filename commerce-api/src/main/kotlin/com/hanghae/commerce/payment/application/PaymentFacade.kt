package com.hanghae.commerce.payment.application

import com.hanghae.commerce.order.domain.OrderReader
import com.hanghae.commerce.payment.domain.PaymentService
import com.hanghae.commerce.payment.domain.command.PaymentCommand
import com.hanghae.commerce.payment.presentation.dto.PaymentRequest
import com.hanghae.commerce.payment.presentation.dto.PaymentResponse
import org.springframework.stereotype.Component

@Component
class PaymentFacade(
    private val paymentService: PaymentService,
    private val orderReader: OrderReader,
) {
    fun payment(paymentRequest: PaymentRequest): PaymentResponse {
        return PaymentResponse(
            paymentService.payment(
                PaymentCommand(
                    order = orderReader.read(paymentRequest.orderId),
                    bankAccount = paymentRequest.bankAccount,
                ),
            ),
        )
    }
}
