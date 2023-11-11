package com.hanghae.commerce.user.domain

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {
    fun findById(id: String): User? {
        return userRepository.findById(id)
    }

    fun countUserByAccount(account: String): Integer {
        return userRepository.countUserByAccount(account)
    }

    fun findByAccount(account: String): User? {
        return userRepository.findByAccount(account)
    }
}
