package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderItem

fun OrderEntity.toDomain() : Order {
    return Order(
        id = this.id,
        orderAmount = this.orderAmount,
        discountAmount = this.discountAmount,
        paymentAmount = this.paymentAmount,
        deliveryFee = this.deliveryFee,
        orderItemList = this.orderItemList.map { it.toDomain() },
    )
}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        id = this.id,
        orderAmount = this.orderAmount,
        discountAmount = this.discountAmount,
        paymentAmount = this.paymentAmount,
        deliveryFee = this.deliveryFee,
        orderItemList = this.orderItemList.map { it.toEntity() },
    )

}

fun OrderItemEntity.toDomain(): OrderItem {
    return OrderItem(
        id = this.id,
        itemId = this.itemId,
        name = this.name,
        price = this.price,
        orderId = this.order!!.id,
        quantity = this.quantity,
    )
}

fun OrderItem.toEntity(): OrderItemEntity {
    return OrderItemEntity(
        id = this.id,
        itemId = this.itemId,
        quantity = this.quantity,
        name = this.name,
        price = this.price,
        order = this.orderId?.let { OrderEntity.from(it) },
    )
}
