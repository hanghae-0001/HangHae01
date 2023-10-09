package com.hanghae.commerce.redis.lock

import com.hanghae.commerce.lock.Lock
import org.redisson.api.RLock
import java.util.concurrent.TimeUnit

class RedissonLock(
    private val rLock: RLock,
) : Lock {
    override fun tryLock(waitTime: Long, leaseTime: Long, timeUnit: TimeUnit): Boolean {
        return rLock.tryLock()
    }

    override fun unlock() {
        rLock.unlock()
    }
}
