package com.hanghae.health.domain.user

import com.hanghae.health.domain.order.Order
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (
    val name: String,

    val phone: String,

    val address: String,

    val userType: UserType,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: MutableList<Order> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }

    companion object {
        fun fixture(
            name: String = "이름",
            phone: String = "전화번호",
            address: String = "주소",
            userType: UserType = UserType.BUYER,
            id: Long? = null,
        ): User {
            return User(
                name = name,
                phone = phone,
                address = address,
                userType = userType,
                id = id,
            )
        }
    }
}
