package com.hanghae.health.domain.product

import com.hanghae.health.domain.order.Order
import jakarta.persistence.*

@Entity
class Product(
    val name: String,

    val category: Category,

    val stockCount: Int,

    @ManyToOne
    val order: Order?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

}
