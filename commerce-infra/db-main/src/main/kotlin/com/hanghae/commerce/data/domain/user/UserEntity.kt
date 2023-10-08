package com.hanghae.commerce.data.domain.user

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserType
import jakarta.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "users")
class UserEntity(
    @Transient
    val identifier: String,
    val name: String,
    val age: Int,
    val email: String,
    val address: String,
    @Enumerated(EnumType.STRING)
    val userType: UserType,
) : PrimaryKeyEntity(identifier) {
    fun toDomain(): User {
        return User(
            id = this.id,
            name = this.name,
            age = this.age,
            email = this.email,
            address = this.address,
            userType = this.userType,
        )
    }
}
