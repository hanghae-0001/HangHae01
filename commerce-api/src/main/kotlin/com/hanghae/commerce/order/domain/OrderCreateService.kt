package com.hanghae.commerce.order.domain

import com.hanghae.commerce.event.CommerceEventPublisher
import com.hanghae.commerce.order.application.OrderWriter
import com.hanghae.commerce.order.domain.command.OrderCreateCommand
import org.springframework.stereotype.Service

@Service
class OrderCreateService(
    private val eventPublisher: CommerceEventPublisher,
    private val orderWriter: OrderWriter,
) {
    fun create(orderCreateCommand: OrderCreateCommand): String {
        val order: Order = Order.from(orderCreateCommand)

        val createdOrder = orderWriter.write(order)

        publishOrderCreatedEvent(createdOrder)
        return createdOrder.id
    }

    private fun publishOrderCreatedEvent(order: Order) {
        eventPublisher.publish(OrderCreateEvent.from(order))
    }
}
