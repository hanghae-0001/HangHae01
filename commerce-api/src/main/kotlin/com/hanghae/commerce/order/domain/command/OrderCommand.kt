package com.hanghae.commerce.order.domain.command

data class OrderCommand(
    val userId: String,
    val orderItemList: List<OrderItem>,
) {
    data class OrderItem(
        val itemId: String,
        val quantity: Int,
    )
}
