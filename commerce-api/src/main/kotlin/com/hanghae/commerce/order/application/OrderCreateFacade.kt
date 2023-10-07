package com.hanghae.commerce.order.application

import com.hanghae.commerce.item.application.ItemReader
import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.order.domain.OrderCreateService
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.domain.command.OrderCreateCommand
import com.hanghae.commerce.order.presentaion.dto.OrderCreateRequest
import com.hanghae.commerce.order.presentaion.dto.OrderCreateResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderCreateFacade(
    private val orderCreateService: OrderCreateService,
    private val itemReader: ItemReader,
) {

    @Transactional
    fun create(request: OrderCreateRequest): OrderCreateResponse {
        return OrderCreateResponse(
            orderCreateService.create(
                OrderCreateCommand(
                    mapOrderItems(request),
                ),
            ),
        )
    }

    private fun mapOrderItems(request: OrderCreateRequest) =
        findItems(request.itemList)
            .map { item ->
                OrderItem(
                    itemId = item.id,
                    name = item.name,
                    orderId = null,
                    price = item.price,
                    quantity = request.itemList.findLast { it.id == item.id }!!.quantity,
                )
            }

    private fun findItems(requestOrderItemList: List<OrderCreateRequest.Item>): List<Item> =
        itemReader.read(requestOrderItemList.map { it.id })
}
