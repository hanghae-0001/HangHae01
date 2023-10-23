package com.hanghae.commerce.claim.domain

import com.hanghae.commerce.claim.domain.command.OrderCancelCommand
import com.hanghae.commerce.event.CommerceEventPublisher
import com.hanghae.commerce.order.domain.OrderWriter
import com.hanghae.commerce.order.domain.Order
import org.springframework.stereotype.Service

@Service
class OrderCancelService(
    private val orderWriter: OrderWriter,
    private val eventPublisher: CommerceEventPublisher,
) {
    fun cancel(command: OrderCancelCommand, order: Order) {
        order.cancel(command.reason)
        orderWriter.write(order)
        eventPublisher.publish(
            OrderCancelCompletedEvent(
                orderId = command.orderId,
                bankAccount = command.bankAccount,
            ),
        )
    }
}
