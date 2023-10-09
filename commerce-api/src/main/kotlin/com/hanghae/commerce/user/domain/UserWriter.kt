package com.hanghae.commerce.user.domain

import org.springframework.stereotype.Component

@Component
class UserWriter(
    private val userRepository: UserRepository,
) {
    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun allDelete() {
        return userRepository.allDelete()
    }
}
