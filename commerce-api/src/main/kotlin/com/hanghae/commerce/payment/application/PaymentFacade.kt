package com.hanghae.commerce.payment.application

import com.hanghae.commerce.order.domain.OrderReader
import com.hanghae.commerce.payment.domain.PaymentService
import com.hanghae.commerce.payment.domain.command.PaymentCommand
import org.springframework.stereotype.Component

@Component
class PaymentFacade(
    private val paymentService: PaymentService,
    private val orderReader: OrderReader,
) {
    fun payment(command: PaymentCommand): String {
        return paymentService.payment(
            command,
            orderReader.read(command.orderId),
        )
    }
}
