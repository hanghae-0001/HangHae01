package com.hanghae.commerce.order.domain

import com.hanghae.commerce.order.application.OrderReader
import com.hanghae.commerce.order.application.OrderWriter
import com.hanghae.commerce.payment.domain.PaymentCompletedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PaymentCompletedEventListener(
    private val orderWriter: OrderWriter,
    private val orderReader: OrderReader,
) {
    @EventListener
    fun onPaymentCompleted(event: PaymentCompletedEvent) {
        val order = orderReader.read(event.orderId)
        order.completePayment()
        orderWriter.write(order)
    }
}