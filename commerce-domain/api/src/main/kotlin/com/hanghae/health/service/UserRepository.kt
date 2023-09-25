package com.hanghae.health.service

interface UserRepository {

    fun save(user: User)
    fun read(id: Long): User?
}
