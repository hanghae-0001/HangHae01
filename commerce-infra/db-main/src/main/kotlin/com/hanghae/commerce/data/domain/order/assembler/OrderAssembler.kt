package com.hanghae.commerce.data.domain.order.assembler

import com.hanghae.commerce.data.domain.order.OrderEntity
import com.hanghae.commerce.data.domain.order.OrderItemEntity
import com.hanghae.commerce.order.domain.Order
import org.springframework.stereotype.Component

@Component
class OrderAssembler(
    private val orderItemAssembler: OrderItemAssembler,
) {

    fun toDomain(entity: OrderEntity) : Order {
        return Order(
            id = entity.id,
            orderAmount = entity.orderAmount,
            discountAmount = entity.discountAmount,
            paymentAmount = entity.paymentAmount,
            deliveryFee = entity.deliveryFee,
            orderItemList = entity.orderItemList.map { orderItemAssembler.toDomain(it) },
        )
    }

    fun toEntity(domain: Order): OrderEntity {
        return OrderEntity(
            id = domain.id,
            orderAmount = domain.orderAmount,
            discountAmount = domain.discountAmount,
            paymentAmount = domain.paymentAmount,
            deliveryFee = domain.deliveryFee,
            orderItemList = domain.orderItemList.map { orderItemAssembler.toEntity(it) },
        )

    }
}
