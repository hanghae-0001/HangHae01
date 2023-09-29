package com.hanghae.commerce.data.domain

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserEntityRepository(
    private val jpaUserRepository: JpaUserRepository,
) : UserRepository {

    override fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun read(id: Long): User? {
        TODO("Not yet implemented")
    }
}
