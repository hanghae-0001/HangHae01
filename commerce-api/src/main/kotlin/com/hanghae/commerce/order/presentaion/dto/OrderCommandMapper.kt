package com.hanghae.commerce.order.presentaion.dto

import com.hanghae.commerce.order.domain.command.OrderCommand

fun OrderRequest.toCommand(): OrderCommand {
    return OrderCommand(
        userId = this.userId,
        orderItemList = this.itemList.map {
            OrderCommand.OrderItem(
                itemId = it.id,
                quantity = it.quantity,
            )
        },
    )
}
