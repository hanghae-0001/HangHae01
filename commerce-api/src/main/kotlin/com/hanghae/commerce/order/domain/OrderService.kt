package com.hanghae.commerce.order.domain

import com.hanghae.commerce.event.EventPublisher
import com.hanghae.commerce.order.domain.command.OrderCommand
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val eventPublisher: EventPublisher,
    private val orderFactory: OrderFactory,
    private val orderWriter: OrderWriter,
) {
    fun create(orderCommand: OrderCommand): String {
        val order = orderWriter.write(
            orderFactory.create(orderCommand),
        )

        publishOrderCreatedEvent(order)
        return order.id
    }

    private fun publishOrderCreatedEvent(order: Order) {
        eventPublisher.publish(OrderedEvent(order))
    }
}
