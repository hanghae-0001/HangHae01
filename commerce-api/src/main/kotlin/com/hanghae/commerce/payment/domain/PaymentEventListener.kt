package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.claim.domain.OrderCancelCompletedEvent
import com.hanghae.commerce.order.application.OrderReader
import com.hanghae.commerce.payment.application.PaymentReader
import com.hanghae.commerce.payment.domain.command.PaymentRefundCommand
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PaymentEventListener(
    private val paymentService: PaymentService,
    private val orderReader: OrderReader,
    private val paymentReader: PaymentReader,
) {
    @EventListener
    fun onOrderCanceled(event: OrderCancelCompletedEvent) {
        val order = orderReader.read(event.orderId)
        val payment = paymentReader.readByOrderId(event.orderId)

        if (order.isPaymentWait()) {
            payment.cancel()
            return
        }

        paymentService.refund(
            command = PaymentRefundCommand(
                orderId = order.id,
                payment = payment,
                bankAccount = event.bankAccount,
            ),
            order = order,
        )
    }
}
