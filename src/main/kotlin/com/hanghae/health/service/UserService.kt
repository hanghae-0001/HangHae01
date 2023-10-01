package com.example.demo.service;

import com.example.demo.repository.UserEntityRepository
import com.hanghae.health.domain.UserEntity
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserEntityRepository
) {

    fun getUsers(): List<UserEntity> {
        return userRepository.findAll()
    }
}