package com.hanghae.commerce.redis.lock

import com.hanghae.commerce.lock.Lock
import com.hanghae.commerce.lock.LockRepository
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Repository

@Repository
class RedisLockRepository(
    private val redissonClient: RedissonClient,
) : LockRepository {
    override fun get(key: String): Lock {
        return RedissonLock(redissonClient.getLock(key))
    }
}
