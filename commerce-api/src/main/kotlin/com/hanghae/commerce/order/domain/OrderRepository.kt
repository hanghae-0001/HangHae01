package com.hanghae.commerce.order.domain

interface OrderRepository {
    fun save(order: Order): Order
    fun findById(id: String): Order?
    fun deleteAll()
}
