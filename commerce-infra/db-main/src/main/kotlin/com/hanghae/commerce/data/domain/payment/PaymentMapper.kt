package com.hanghae.commerce.data.domain.payment

import com.hanghae.commerce.data.domain.order.toDomain
import com.hanghae.commerce.data.domain.order.toEntity
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.payment.domain.Payment

    fun Payment.toEntity(): PaymentEntity {
        return PaymentEntity(
            id = this.id,
            order = this.order.toEntity(),
            cardNumber = this.cardNumber,
        )
    }

    fun PaymentEntity.toDomain(orderItemList: List<OrderItem>): Payment {
        return Payment(
            id = this.id,
            order = this.order.toDomain(orderItemList),
            cardNumber = this.cardNumber,
        )
    }
