package com.hanghae.commerce.order.application

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderRepository
import org.springframework.stereotype.Component

@Component
class OrderWriter(
    private val orderRepository: OrderRepository,
) {
    fun write(order: Order): Order {
        return orderRepository.save(order)
    }
}
