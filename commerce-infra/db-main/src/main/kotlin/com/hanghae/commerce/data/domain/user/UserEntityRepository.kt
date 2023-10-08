package com.hanghae.commerce.data.domain.user

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserEntityRepository(
    private val jpaUserRepository: JpaUserRepository,
) : UserRepository {

    override fun save(user: User): User {
        return jpaUserRepository.save(user.toEntity()).toDomain()
    }

    override fun findById(id: String): User? {
        return jpaUserRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun allDelete() {
        jpaUserRepository.deleteAllInBatch()
    }
}
