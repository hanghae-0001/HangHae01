package com.hanghae.commerce.data.domain.order.assembler

import com.hanghae.commerce.data.domain.order.OrderEntity
import com.hanghae.commerce.data.domain.order.OrderItemEntity
import com.hanghae.commerce.order.domain.OrderItem
import org.springframework.stereotype.Component

@Component
class OrderItemAssembler {

    fun toDomain(entity: OrderItemEntity): OrderItem {
        return OrderItem(
            id = entity.id,
            itemId = entity.itemId,
            name = entity.name,
            price = entity.price,
            orderId = entity.order!!.id,
            quantity = entity.quantity,
        )
    }

    fun toEntity(doamin: OrderItem): OrderItemEntity {
        return OrderItemEntity(
            id = doamin.id,
            itemId = doamin.itemId,
            quantity = doamin.quantity,
            name = doamin.name,
            price = doamin.price,
            order = doamin.orderId?.let { OrderEntity.from(it) },
        )
    }
}
