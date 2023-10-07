package com.hanghae.commerce.user.domain

import org.springframework.stereotype.Repository

@Repository
interface UserRepository {
    fun save(user: User): Long
    fun read(id: Long): User?
    fun allDelete()
}
