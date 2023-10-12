package com.hanghae.commerce.order.domain.command

import com.hanghae.commerce.order.domain.OrderItem

class OrderCreateCommand(
    val orderItemList: List<OrderItem>,
)
