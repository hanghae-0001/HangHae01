package com.hanghae.commerce.data.domain

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserType
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    val name: String,
    val age: Int,
    val email: String,
    val address: String,
    @Enumerated(EnumType.STRING)
    val userType: UserType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {
    companion object {
        fun from(user: User): UserEntity {
            return UserEntity(
                name = user.name,
                age = user.age,
                email = user.email,
                address = user.address,
                userType = user.userType,
            )
        }
    }
}
