package com.hanghae.commerce.order.domain

import com.hanghae.commerce.common.IdentifierConstants
import com.hanghae.commerce.order.domain.command.OrderCreateCommand

class Order(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val orderAmount: Int,
    val discountAmount: Int,
    val paymentAmount: Int,
    val deliveryFee: Int,
    val orderItemList: List<OrderItem>,
) {
    companion object {
        fun from(orderCreateCommand: OrderCreateCommand): Order {
            val orderItemList = orderCreateCommand.orderItemList

            val orderAmount: Int = calculateOrderAmount(orderItemList)
            val deliveryFee: Int = calculateDeliveryFee(orderAmount)
            val paymentAmount: Int = orderAmount + deliveryFee

            return Order(
                orderAmount = orderAmount,
                discountAmount = 0,
                paymentAmount = paymentAmount,
                deliveryFee = deliveryFee,
                orderItemList = orderItemList,
            )
        }

        private fun calculateDeliveryFee(orderAmount: Int): Int {
            return if (orderAmount < 50000) 2500 else 0
        }

        private fun calculateOrderAmount(orderLines: List<OrderItem>): Int {
            return orderLines.sumOf { it.getAmount() }
        }
    }
}
