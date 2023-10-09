package com.hanghae.commerce.lock

import java.util.concurrent.TimeUnit

interface Lock {
    fun tryLock(waitTime: Long, leaseTime: Long, timeUnit: TimeUnit): Boolean
    fun unlock()
}
