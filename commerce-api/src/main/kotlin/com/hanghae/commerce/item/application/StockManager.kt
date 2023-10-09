package com.hanghae.commerce.item.application

import com.hanghae.commerce.aop.lock.DistributedLock
import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.exception.SoldOutException
import org.springframework.stereotype.Component

@Component
class StockManager(
    private val itemReader: ItemReader,
    private val itemWriter: ItemWriter,
) {

    @DistributedLock(key = "#orderItem.itemId")
    fun verifyStockRemains(orderItem: OrderItem) {
        val item: Item = itemReader.read(orderItem.itemId) ?: throw IllegalArgumentException()
        checkStockAndUpdateQuantity(item, orderItem)
    }

    private fun checkStockAndUpdateQuantity(
        item: Item,
        orderItem: OrderItem,
    ) {
        if (item.stock - orderItem.quantity < 0) {
            throw SoldOutException("재고가 부족합니다.")
        }
        item.stock -= orderItem.quantity
        itemWriter.write(item)
    }
}
