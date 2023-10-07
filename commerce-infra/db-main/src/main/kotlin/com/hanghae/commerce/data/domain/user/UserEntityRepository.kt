package com.hanghae.commerce.data.domain.user

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserEntityRepository(
    private val jpaUserRepository: JpaUserRepository,
) : UserRepository {

    @Transactional
    override fun save(user: User): Long {
        return jpaUserRepository.save(
            UserEntity.from(user),
        ).id!!
    }

    @Transactional(readOnly = true)
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
