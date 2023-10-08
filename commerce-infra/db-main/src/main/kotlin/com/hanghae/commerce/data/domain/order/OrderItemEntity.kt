package com.hanghae.commerce.data.domain.order

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import jakarta.persistence.*

@Entity
@Table(name = "order_item")
class OrderItemEntity(
    @Transient
    val identifier: String,

    @Column(nullable = false)
    val itemId: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val quantity: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: OrderEntity,

) : PrimaryKeyEntity(identifier)
