package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.domain.OrderRepository
import org.springframework.stereotype.Repository

@Repository
class OrderEntityRepository(
    private val jpaOrderRepository: JpaOrderRepository,
    private val jpaOrderItemRepository: JpaOrderItemRepository,
) : OrderRepository {

    override fun save(order: Order): Order {
        val orderEntity = order.toEntity()
        val savedEntity = jpaOrderRepository.save(orderEntity)
        val orderItems = saveOrderItemEntityList(order.orderItemList, savedEntity.id).map { it.toDomain() }
        return savedEntity.toDomain(orderItems)
    }

    private fun saveOrderItemEntityList(
        orderItemList: List<OrderItem>,
        orderId: String,
    ): MutableList<OrderItemEntity> {
        return jpaOrderItemRepository.saveAll(
            orderItemList.map { it.toEntity(orderId) },
        )
    }

    override fun findById(id: String): Order? {
        val orderEntity = jpaOrderRepository.findById(id).orElse(null) ?: return null
        val orderItemList = jpaOrderItemRepository.findByOrder(orderEntity).map { it.toDomain() }
        return orderEntity.toDomain(orderItemList)
    }

    override fun deleteAll() {
        jpaOrderItemRepository.deleteAll()
        jpaOrderRepository.deleteAll()
    }
}
