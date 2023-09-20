package com.hanghae.health.domain.product

import com.hanghae.health.domain.order.Order
import com.hanghae.health.domain.user.User
import com.hanghae.health.domain.user.UserType
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
    companion object {
        fun fixture(
            name: String = "이름",
            category: Category = Category.CLOTHES,
            stockCount: Int = 0,
            order: Order? = null,
            id: Long? = null,
        ): Product {
            return Product(
                name = name,
                category = category,
                stockCount = stockCount,
                order = order,
                id = id,
            )
        }
    }
}
