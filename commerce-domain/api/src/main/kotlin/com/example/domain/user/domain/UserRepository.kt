package com.example.domain.user.domain

interface UserRepository {
    fun save(user: User)
    fun read(id: Long): User?
}
