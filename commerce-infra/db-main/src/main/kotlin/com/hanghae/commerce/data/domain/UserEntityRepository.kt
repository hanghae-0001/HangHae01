package com.hanghae.commerce.data.domain

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserEntityRepository(
    private val jpaUserRepository: JpaUserRepository,
) : UserRepository {

    override fun save(user: User): Long {
        return jpaUserRepository.save(
            UserEntity.toEntity(user),
        ).id!!
    }

    override fun read(id: Long): User? {
        return jpaUserRepository.findByIdOrNull(id)?.let {
            User(
                id = it.id!!,
                name = it.name!!,
                email = it.email!!,
                address = it.address!!,
                age = it.age,
                userType = it.userType,
            )
        }
    }

    override fun allDelete() {
        jpaUserRepository.deleteAllInBatch()
    }
}
