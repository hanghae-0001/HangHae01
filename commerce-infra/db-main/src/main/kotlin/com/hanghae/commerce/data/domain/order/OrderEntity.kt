package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import jakarta.persistence.*

@Entity
@Table(name = "order")
class OrderEntity(
    @Transient private val tempId: String,

    @Column(nullable = false) val orderAmount: Int = 0,

    @Column(nullable = false) val discountAmount: Int = 0,

    @Column(nullable = false) val paymentAmount: Int = 0,

    @Column(nullable = false) val deliveryFee: Int = 0,

    @OneToMany(
        mappedBy = "order",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        orphanRemoval = true,
    ) val orderItemList: List<OrderItemEntity> = listOf(),
) : PrimaryKeyEntity(tempId) {
    companion object {
        fun from(id: String): OrderEntity {
            return OrderEntity(
                tempId = id,
            )
        }
    }
}
