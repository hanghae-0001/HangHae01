package com.hanghae.commerce.order.domain

import com.hanghae.commerce.common.IdentifierConstants
import com.hanghae.commerce.order.domain.command.OrderCreateCommand

class Order(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val userId: String,
    val orderAmount: Int,
    val discountAmount: Int,
    val paymentAmount: Int,
    val deliveryFee: Int,
    var status: OrderStatus,
    val orderItemList: List<OrderItem>,
) {
    fun completePayment() {
        this.status = OrderStatus.DELIVERY_PREPARE
    }

    fun cancel() {
        this.status = OrderStatus.ORDER_CANCEL
    }

    fun isPaymentWait(): Boolean {
        return this.status == OrderStatus.PAYMENT_WAIT
    }

    companion object {
        fun from(orderCreateCommand: OrderCreateCommand): Order {
            val orderItemList = orderCreateCommand.orderItemList

            val orderAmount: Int = calculateOrderAmount(orderItemList)
            val deliveryFee: Int = calculateDeliveryFee(orderAmount)
            val paymentAmount: Int = orderAmount + deliveryFee

            return Order(
                userId = orderCreateCommand.userId,
                orderAmount = orderAmount,
                discountAmount = 0,
                paymentAmount = paymentAmount,
                deliveryFee = deliveryFee,
                status = OrderStatus.PAYMENT_WAIT,
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
