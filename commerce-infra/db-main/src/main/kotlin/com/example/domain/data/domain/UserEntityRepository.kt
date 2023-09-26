package com.example.domain.data.domain

import com.example.domain.service.User
import com.example.domain.service.UserRepository
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
