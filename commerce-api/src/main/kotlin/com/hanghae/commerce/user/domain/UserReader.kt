package com.hanghae.commerce.user.domain

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {
    fun findById(id: String): User? {
        return userRepository.findById(id)
    }
}
