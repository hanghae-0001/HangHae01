package com.hanghae.commerce.data.domain.cart

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "cart", uniqueConstraints = [UniqueConstraint(name = "UniqueUser", columnNames = ["userId"])])
class CartEntity(
    @Id val identifier: String,

    @Column(nullable = false)
    val userId: String,

) {

    companion object {
        fun from(userId: String): CartEntity {
            return CartEntity(
                identifier = UUID.randomUUID().toString(),
                userId = userId,
            )
        }
    }
}
