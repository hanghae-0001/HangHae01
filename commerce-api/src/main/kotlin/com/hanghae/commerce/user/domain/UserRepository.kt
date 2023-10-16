package com.hanghae.commerce.user.domain

interface UserRepository {
    fun save(user: User): User
    fun findById(id: String): User?
    fun allDelete()
}
