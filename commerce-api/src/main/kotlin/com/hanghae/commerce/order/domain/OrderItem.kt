package com.hanghae.commerce.order.domain

import com.hanghae.commerce.common.IdentifierConstants

class OrderItem(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val itemId: String,
    var orderId: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val name: String,
    val price: Int,
    val quantity: Int,
) {
    init {
        if (this.quantity == 0) {
            throw IllegalArgumentException("수량을 0개인 상품은 주문할 수 없습니다.")
        }
    }

    fun getAmount(): Int {
        return price * quantity
    }
}
