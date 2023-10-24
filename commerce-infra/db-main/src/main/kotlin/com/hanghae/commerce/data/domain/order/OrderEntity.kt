package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import com.hanghae.commerce.order.domain.OrderStatus
import jakarta.persistence.*

@Entity
@Table(name = "orders")
class OrderEntity(
    @Transient private val identifier: String,

    @Column(nullable = false)
    val userId: String? = null,

    @Column(nullable = false)
    val orderAmount: Int = 0,

    @Column(nullable = false)
    val discountAmount: Int = 0,

    @Column(nullable = false)
    val paymentAmount: Int = 0,

    @Column(nullable = false)
    val deliveryFee: Int = 0,

    @Column(nullable = true)
    val cancelReason: String? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: OrderStatus = OrderStatus.PAYMENT_WAIT,
) : PrimaryKeyEntity(identifier) {

    companion object {
        fun from(id: String): OrderEntity {
            return OrderEntity(
                identifier = id,
            )
        }
    }
}
