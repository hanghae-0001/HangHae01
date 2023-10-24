package com.hanghae.commerce.order.application

import com.hanghae.commerce.item.application.ItemStockService
import com.hanghae.commerce.order.domain.OrderService
import com.hanghae.commerce.order.domain.command.OrderCommand
import org.springframework.stereotype.Service

@Service
class OrderFacade(
    private val orderService: OrderService,
    private val itemStockService: ItemStockService,
) {
    fun order(command: OrderCommand): String {
        verifyStockRemains(command.orderItemList)
        return orderService.create(command)
    }

    private fun verifyStockRemains(orderItems: List<OrderCommand.OrderItem>) {
        orderItems.forEach {
            itemStockService.verifyStockRemains(it)
        }
    }
}
