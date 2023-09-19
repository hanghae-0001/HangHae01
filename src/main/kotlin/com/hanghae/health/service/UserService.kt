package com.example.demo.service;

import com.example.demo.repository.UserRepository
import com.hanghae.health.domain.User
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }
}
