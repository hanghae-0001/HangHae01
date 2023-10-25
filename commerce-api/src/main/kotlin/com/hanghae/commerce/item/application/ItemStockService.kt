package com.hanghae.commerce.item.application

import com.hanghae.commerce.aop.lock.DistributedLock
import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemReader
import com.hanghae.commerce.item.domain.ItemWriter
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.domain.command.OrderCommand
import com.hanghae.commerce.order.exception.SoldOutException
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class ItemStockService(
    private val itemReader: ItemReader,
    private val itemWriter: ItemWriter,
) {

    @DistributedLock(
        key = "#orderItemCommand.itemId",
        timeUnit = TimeUnit.SECONDS,
        waitTime = 10,
        leaseTime = 60,
    )
    fun verifyStockRemains(orderItemCommand: OrderCommand.OrderItem) {
        val item: Item = itemReader.getItemByItemId(orderItemCommand.itemId) ?: throw IllegalArgumentException("존재하지 않는 상품입니다.")
        checkStockAndUpdateQuantity(item, orderItemCommand.quantity)
    }

    private fun checkStockAndUpdateQuantity(
        item: Item,
        orderItemQuantity: Int,
    ) {
        if (item.stock - orderItemQuantity < 0) {
            throw SoldOutException("재고가 부족합니다.")
        }
        item.stock -= orderItemQuantity
        itemWriter.save(item)
    }

    @DistributedLock(
        key = "#orderItem.itemId",
        timeUnit = TimeUnit.SECONDS,
        waitTime = 10,
        leaseTime = 60,
    )
    fun restore(orderItem: OrderItem) {
        val item: Item = itemReader.getItemByItemId(orderItem.itemId) ?: throw IllegalArgumentException()
        item.stock += orderItem.quantity
        itemWriter.save(item)
    }
}
