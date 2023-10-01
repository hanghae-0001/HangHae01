package com.hanghae.commerce.user.domain

import org.springframework.stereotype.Component

@Component
class UserWriter(
    private val userRepository: UserRepository,
) {
    fun save(user: User): Long {
        return userRepository.save(user)
    }
}
