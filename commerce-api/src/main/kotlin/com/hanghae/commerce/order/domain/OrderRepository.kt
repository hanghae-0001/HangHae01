package com.hanghae.commerce.order.domain

interface OrderRepository {
    fun save(order: Order): Order
}
