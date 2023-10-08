package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import jakarta.persistence.*

@Entity
@Table(name = "orders")
class OrderEntity(
    @Transient
    private val identifier: String,

    @Column(nullable = false)
    val orderAmount: Int = 0,

    @Column(nullable = false)
    val discountAmount: Int = 0,

    @Column(nullable = false)
    val paymentAmount: Int = 0,

    @Column(nullable = false)
    val deliveryFee: Int = 0,

) : PrimaryKeyEntity(identifier) {

    companion object {
        fun from(id: String): OrderEntity {
            return OrderEntity(
                identifier = id,
            )
        }
    }
}
