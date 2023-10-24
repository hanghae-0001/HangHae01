package com.hanghae.commerce.order.domain

import com.hanghae.commerce.common.IdentifierConstants

class Order(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val userId: String,
    val orderAmount: Int,
    val discountAmount: Int,
    val paymentAmount: Int,
    val deliveryFee: Int,
    var cancelReason: String? = null,
    var status: OrderStatus,
    val orderItemList: List<OrderItem>,
) {
    fun completePayment() {
        this.status = OrderStatus.DELIVERY_PREPARE
    }

    fun cancel(reason: String) {
        this.status = OrderStatus.ORDER_CANCEL
        this.cancelReason = reason
    }

    fun isPaymentWait(): Boolean {
        return this.status == OrderStatus.PAYMENT_WAIT
    }
}
