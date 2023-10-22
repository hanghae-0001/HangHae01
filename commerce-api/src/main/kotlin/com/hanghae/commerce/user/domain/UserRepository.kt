package com.hanghae.commerce.user.domain

interface UserRepository {
    fun save(user: User): User
    fun findById(id: String): User?
    fun allDelete()
    fun countUserByAccount(account: String): Integer
    fun findByAccount(account: String): User?
}
