package com.hanghae.commerce.lock

interface LockRepository {

    fun get(key: String): Lock
}
