package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderItem

fun OrderEntity.toDomain(orderItemList: List<OrderItem>): Order {
    return Order(
        id = this.id,
        orderAmount = this.orderAmount,
        discountAmount = this.discountAmount,
        paymentAmount = this.paymentAmount,
        deliveryFee = this.deliveryFee,
        orderItemList = orderItemList,
        status = this.status,
    )
}

fun Order.toEntity(): OrderEntity {
    val orderEntity = OrderEntity(
        identifier = this.id,
        orderAmount = this.orderAmount,
        discountAmount = this.discountAmount,
        paymentAmount = this.paymentAmount,
        deliveryFee = this.deliveryFee,
        status = this.status,
    )
    return orderEntity
}

fun OrderItemEntity.toDomain(): OrderItem {
    return OrderItem(
        id = this.id,
        itemId = this.itemId,
        name = this.name,
        price = this.price,
        orderId = this.order.id,
        quantity = this.quantity,
    )
}

fun OrderItem.toEntity(orderId: String): OrderItemEntity {
    return OrderItemEntity(
        identifier = this.id,
        itemId = this.itemId,
        quantity = this.quantity,
        name = this.name,
        price = this.price,
        order = OrderEntity.from(orderId),
    )
}
