package com.hanghae.commerce.order.domain

import com.hanghae.commerce.item.domain.ItemReader
import com.hanghae.commerce.order.domain.command.OrderCommand
import org.springframework.stereotype.Component

@Component
class OrderFactory(
    private val itemReader: ItemReader,
) {
    fun create(command: OrderCommand): Order {
        val orderItemList = createOrderItems(command.orderItemList)
        val orderAmount: Int = calculateOrderAmount(orderItemList)
        val deliveryFee: Int = calculateDeliveryFee(orderAmount)
        val paymentAmount: Int = orderAmount + deliveryFee

        return Order(
            userId = command.userId,
            orderAmount = orderAmount,
            discountAmount = 0,
            paymentAmount = paymentAmount,
            deliveryFee = deliveryFee,
            status = OrderStatus.PAYMENT_WAIT,
            orderItemList = orderItemList,
        )
    }

    private fun createOrderItems(orderItems: List<OrderCommand.OrderItem>): List<OrderItem> {
        return itemReader.read(orderItems.map { it.itemId }).map {
            OrderItem(
                itemId = it.id,
                name = it.name,
                price = it.price,
                quantity = orderItems.find { orderItem -> orderItem.itemId == it.id }!!.quantity,
            )
        }
    }

    private fun calculateDeliveryFee(orderAmount: Int): Int {
        return if (orderAmount < 50000) 2500 else 0
    }

    private fun calculateOrderAmount(orderLines: List<OrderItem>): Int {
        return orderLines.sumOf { it.getAmount() }
    }
}
