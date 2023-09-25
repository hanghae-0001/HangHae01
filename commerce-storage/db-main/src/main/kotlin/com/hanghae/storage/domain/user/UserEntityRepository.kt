package com.hanghae.storage.domain.user

import com.hanghae.health.service.User
import com.hanghae.health.service.UserRepository
import org.springframework.stereotype.Repository

@Repository
internal class UserEntityRepository(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {
    override fun save(user: User) {
        userJpaRepository.save(UserEntity.from(user))
    }

    override fun read(id: Long): User? {
        val optionalUserEntity = userJpaRepository.findById(id)
        if (optionalUserEntity.isPresent) {
            return optionalUserEntity.get().toUser()
        }
        return null
    }
}
