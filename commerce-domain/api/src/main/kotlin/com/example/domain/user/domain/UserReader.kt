package com.example.domain.user.domain

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {

    fun save(name: String) {
        userRepository.save(User(name = name))
    }

    fun read(id: Long): User? {
        return userRepository.read(id)
    }
}
