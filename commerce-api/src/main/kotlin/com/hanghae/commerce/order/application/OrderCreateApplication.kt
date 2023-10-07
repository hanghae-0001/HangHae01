package com.hanghae.commerce.order.application

import com.hanghae.commerce.order.domain.OrderCreateService
import com.hanghae.commerce.order.presentaion.dto.OrderCreateRequest
import com.hanghae.commerce.order.presentaion.dto.OrderCreateResponse
import org.springframework.stereotype.Service

@Service
class OrderCreateApplication(
    private val orderCreateService: OrderCreateService,
    private val itemReader: ItemReader,
) {

    @Transactional
    fun create(request: OrderCreateRequest): OrderCreateResponse {
        val read = itemReader.read(request.orderItemList.map { it.itemId })
        orderCreateService.create()
    }
}
