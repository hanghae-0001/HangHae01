package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderRepository
import org.springframework.stereotype.Repository

@Repository
class OrderEntityRepository(
    private val jpaOrderRepository: JpaOrderRepository,
) : OrderRepository {

    override fun save(order: Order): Order {
        return jpaOrderRepository.save(order.toEntity()).toDomain()
    }

    override fun deleteAll() {
        jpaOrderRepository.deleteAll()
    }
}
