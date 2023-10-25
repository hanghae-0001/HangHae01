package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderItem

fun OrderEntity.toDomain(orderItemList: List<OrderItem>): Order {
    return Order(
        id = this.id,
        userId = this.userId!!,
        orderAmount = this.orderAmount,
        discountAmount = this.discountAmount,
        paymentAmount = this.paymentAmount,
        deliveryFee = this.deliveryFee,
        orderItemList = orderItemList,
        cancelReason = this.cancelReason,
        status = this.status,
    )
}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        identifier = this.id,
        userId = this.userId,
        orderAmount = this.orderAmount,
        discountAmount = this.discountAmount,
        paymentAmount = this.paymentAmount,
        deliveryFee = this.deliveryFee,
        cancelReason = this.cancelReason,
        status = this.status,
    )
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
