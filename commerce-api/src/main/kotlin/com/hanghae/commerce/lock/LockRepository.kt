package com.hanghae.commerce.lock

interface LockRepository {

    fun lock(key: String, timeout: Long): Boolean

    fun unlock(key: String)
}
