package com.hanghae.commerce.order.domain.command

import com.hanghae.commerce.order.domain.OrderItem

data class OrderCreateCommand(
    val userId: String,
    val orderItemList: List<OrderItem>,
)
