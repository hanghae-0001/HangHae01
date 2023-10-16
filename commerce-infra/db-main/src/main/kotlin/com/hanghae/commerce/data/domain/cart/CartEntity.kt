package com.hanghae.commerce.data.domain.cart

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "cart", uniqueConstraints = [UniqueConstraint(name = "UniqueUser", columnNames = ["user_id"])])
class CartEntity(
    @Id val identifier: String,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

) {

    companion object {
        fun from(userId: Long): CartEntity {
            return CartEntity(
                identifier = UUID.randomUUID().toString(),
                userId = userId,
            )
        }
    }
}
