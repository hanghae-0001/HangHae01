package com.hanghae.commerce.order.application

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderRepository
import org.springframework.stereotype.Component

@Component
class OrderReader(
    private val orderRepository: OrderRepository,
) {
    fun read(orderId: String): Order {
        return orderRepository.findById(orderId) ?: throw IllegalArgumentException("Order Not Found")
    }
}
