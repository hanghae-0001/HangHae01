package com.hanghae.commerce.lock

import java.util.concurrent.TimeUnit

interface Lock {
    fun lock(leaseTime: Long, timeUnit: TimeUnit)
    fun tryLock(waitTime: Long, leaseTime: Long, timeUnit: TimeUnit): Boolean
    fun unlock()
}
