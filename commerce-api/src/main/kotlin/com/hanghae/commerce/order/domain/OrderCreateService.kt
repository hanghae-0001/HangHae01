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
    fun create(orderCreateCommand: OrderCreateCommand) {
        verifyStockRemains(orderCreateCommand.orderItemList)

        val order: Order = Order.from(orderCreateCommand)

        orderWriter.write(order)
//        publishOrderCreatedEvent(order)
        // -> 알림
        // -> 결제 -> 배송
    }

    private fun publishOrderCreatedEvent(order: Order) {
        eventPublisher.publishEvent(OrderCreateEvent.from(order))
    }

    private fun verifyStockRemains(orderLines: List<OrderItem>) {
        stockManager.verifyStockRemains(orderLines)
    }
}
