package com.hanghae.commerce.order.domain

import com.hanghae.commerce.item.application.StockManager
import com.hanghae.commerce.order.application.OrderWriter
import com.hanghae.commerce.order.domain.command.OrderCreateCommand
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class OrderCreateService(
    private val eventPublisher: ApplicationEventPublisher,
    private val orderWriter: OrderWriter,
    private val stockManager: StockManager,
) {
    fun create(orderCreateCommand: OrderCreateCommand): String {
        verifyStockRemains(orderCreateCommand.orderItemList)

        val order: Order = Order.from(orderCreateCommand)

        val createdOrder = orderWriter.write(order)

        // publishOrderCreatedEvent(createdOrder)
        // -> 알림
        // -> 결제 -> 배송

        return createdOrder.id
    }

    private fun publishOrderCreatedEvent(order: Order) {
        eventPublisher.publishEvent(OrderCreateEvent.from(order))
    }

    private fun verifyStockRemains(orderLines: List<OrderItem>) {
        stockManager.verifyStockRemains(orderLines)
    }
}
