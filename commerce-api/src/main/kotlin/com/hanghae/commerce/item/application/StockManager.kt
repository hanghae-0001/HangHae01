package com.hanghae.commerce.item.application

import org.springframework.stereotype.Component

@Component
class StockManager(
    private val itemReader: ItemReader,
    private val itemWriter: ItemWriter,
//    private val lockHandler: LockHandler,
) {
//    fun verifyStockRemains(orderItemList: List<OrderItem>) {
//        val itemList: List<Item> = itemReader.read(orderItemList.map { it.itemId })
//
//        orderItemList.forEach {orderItem ->
//            val item = itemList.first { it.id == orderItem.itemId }
//
//            lockHandler.requireLock(item.id) {
//                checkStockAndUpdateQuantity(item, orderItem)
//            }
//        }
//    }
//
//    private fun checkStockAndUpdateQuantity(
//        item: Item,
//        orderItem: OrderItem,
//    ) {
//        if (item.stock - orderItem.quantity < 0) {
//            throw SoldOutException("재고가 부족합니다.")
//        }
//        item.stock -= orderItem.quantity
//        itemWriter.write(item)
//    }
}
