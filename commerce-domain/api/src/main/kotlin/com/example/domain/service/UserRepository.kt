package com.example.domain.service

interface UserRepository {
    fun save(user: User)
    fun read(id: Long): User?
}
