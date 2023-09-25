package com.hanghae.storage.domain.user

import com.hanghae.health.service.User
import jakarta.persistence.*

@Entity
@Table(name = "users")
internal class UserEntity(
    initialName: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var name: String = initialName
        protected set

    fun toUser(): User? {
        return User(
            id = id,
            name = name,
        )
    }

    companion object {
        fun from(user: User): UserEntity {
            return UserEntity(
                initialName = user.name,
            )
        }
    }
}
