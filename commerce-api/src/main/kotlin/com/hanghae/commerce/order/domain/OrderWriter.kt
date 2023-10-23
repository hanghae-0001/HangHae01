package com.hanghae.commerce.order.domain

import org.springframework.stereotype.Component

@Component
class OrderWriter(
    private val orderRepository: OrderRepository,
) {
    fun write(order: Order): Order {
        return orderRepository.save(order)
    }
}
