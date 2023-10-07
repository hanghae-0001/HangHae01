package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.lock.LockHandler
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.exception.SoldOutException
import org.springframework.stereotype.Component
import java.util.*

@Component
class StockManager(
    private val itemReader: itemReader,
    private val itemWriter: ItemWriter,
    private val lockHandler: LockHandler,
) {
    fun verifyStockRemains(orderItemList: List<OrderItem>) {
        val itemList: List<Item> = itemReader.read(orderItemList.map { it.itemId })

        orderItemList.forEach {orderItem ->
            val item = itemList.first { it.id == orderItem.itemId }

            lockHandler.requireLock(item.id) {
                checkStockAndUpdateQuantity(item, orderItem)
            }
        }
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
