package com.hanghae.commerce.order.application

import com.hanghae.commerce.item.application.ItemReader
import com.hanghae.commerce.item.application.StockManager
import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.order.domain.OrderCreateService
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.domain.command.OrderCreateCommand
import com.hanghae.commerce.order.presentaion.dto.OrderCreateRequest
import com.hanghae.commerce.order.presentaion.dto.OrderCreateResponse
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class OrderCreateFacade(
    private val orderCreateService: OrderCreateService,
    private val itemReader: ItemReader,
    private val stockManager: StockManager,
) {

    val logger = KotlinLogging.logger {}
    fun create(request: OrderCreateRequest): OrderCreateResponse {
        logger.info { "OrderCreateFacade.create()" }
        val orderItems = orderItemsRequestToDomain(request)

        verifyStockRemains(orderItems)

        return OrderCreateResponse(
            orderCreateService.create(
                OrderCreateCommand(orderItems),
            ),
        )
    }

    private fun verifyStockRemains(orderItems: List<OrderItem>) {
        orderItems.forEach {
            stockManager.verifyStockRemains(it)
        }
    }

    private fun orderItemsRequestToDomain(request: OrderCreateRequest): List<OrderItem> {
        return findItems(request.itemList).map { item ->
            OrderItem(
                itemId = item.id,
                name = item.name,
                price = item.price,
                quantity = request.itemList.findLast { it.id == item.id }!!.quantity,
            )
        }
    }

    private fun findItems(requestOrderItemList: List<OrderCreateRequest.Item>): List<Item> {
        val itemList = itemReader.read(requestOrderItemList.map { it.id })
        if (itemList.size < requestOrderItemList.size) {
            throw IllegalArgumentException("존재하지 않는 상품입니다.")
        }
        return itemList
    }
}
