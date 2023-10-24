package com.hanghae.commerce.item.domain

import com.hanghae.commerce.claim.domain.OrderCancelCompletedEvent
import com.hanghae.commerce.item.application.ItemStockService
import com.hanghae.commerce.order.domain.OrderReader
import com.hanghae.commerce.order.domain.OrderItem
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ItemStockEventListener(
    private val orderReader: OrderReader,
    private val itemStockService: ItemStockService,
) {
    @EventListener
    fun onOrderCanceled(event: OrderCancelCompletedEvent) {
        val orderItemList = orderReader.read(event.orderId).orderItemList
        restoreItemStock(orderItemList)
    }

    private fun restoreItemStock(orderItemList: List<OrderItem>) {
        orderItemList.forEach(itemStockService::restore)
    }
}
