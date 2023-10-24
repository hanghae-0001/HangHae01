package com.hanghae.commerce.order.domain

import com.hanghae.commerce.event.EventPublisher
import com.hanghae.commerce.order.domain.command.OrderCreateCommand
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val eventPublisher: EventPublisher,
    private val orderWriter: OrderWriter,
) {
    fun create(orderCreateCommand: OrderCreateCommand): String {
        val order: Order = Order.from(orderCreateCommand)

        val createdOrder = orderWriter.write(order)

        publishOrderCreatedEvent(createdOrder)
        return createdOrder.id
    }

    private fun publishOrderCreatedEvent(order: Order) {
        eventPublisher.publish(OrderedEvent(order))
    }
}
