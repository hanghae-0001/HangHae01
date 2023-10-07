package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.data.domain.order.assembler.OrderAssembler
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderRepository
import org.springframework.stereotype.Repository

@Repository
class OrderEntityRepository(
    private val assembler: OrderAssembler,
    private val jpaOrderRepository: JpaOrderRepository
): OrderRepository {

    override fun save(order: Order): Order {
        return assembler.toDomain(
            jpaOrderRepository.save(assembler.toEntity(order))
        )
    }
}
