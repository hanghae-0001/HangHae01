package com.hanghae.commerce.data.domain.payment

import com.hanghae.commerce.data.domain.order.OrderEntity
import com.hanghae.commerce.payment.domain.Payment

fun Payment.toEntity(): PaymentEntity {
    return PaymentEntity(
        identifier = this.id,
        order = OrderEntity.from(this.orderId),
        paymentAmount = this.paymentAmount,
        status = status,
    )
}

fun PaymentEntity.toDomain(): Payment {
    return Payment(
        id = this.id,
        orderId = this.order.id,
        paymentAmount = this.paymentAmount,
        status = this.status,
    )
}
