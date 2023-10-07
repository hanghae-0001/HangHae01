package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import com.hanghae.commerce.order.domain.Order
import jakarta.persistence.*

@Entity
@Table(name = "order")
class OrderEntity (
    @Transient
    private val id: String,

    @Column(nullable = false)
    val orderAmount: Int = 0,

    @Column(nullable = false)
    val discountAmount: Int = 0,

    @Column(nullable = false)
    val paymentAmount: Int = 0,

    @Column(nullable = false)
    val deliveryFee: Int = 0,

    @OneToMany(
        mappedBy = "order",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        orphanRemoval = true,
    )
    val orderItemList: List<OrderItemEntity> = listOf(),

) : PrimaryKeyEntity(id) {
    companion object {
        fun from(id: String): OrderEntity {
            return OrderEntity(
                id = id,
            )
        }
    }
}
