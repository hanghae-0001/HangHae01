package com.hanghae.commerce.data.domain.order

import org.springframework.data.jpa.repository.JpaRepository

interface JpaOrderItemRepository : JpaRepository<OrderItemEntity, String> {
    fun findByOrder(orderEntity: OrderEntity): List<OrderItemEntity>
}
