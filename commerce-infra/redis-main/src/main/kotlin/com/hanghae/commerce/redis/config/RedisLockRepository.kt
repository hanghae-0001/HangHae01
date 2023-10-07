package com.hanghae.commerce.redis.config

import com.hanghae.commerce.lock.LockRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisLockRepository(
    private val redisTemplate: RedisTemplate<String, Any>
) : LockRepository {

    override fun lock(key: String, timeout: Long): Boolean {
        val expireTime = System.currentTimeMillis() + timeout
        val isLock = redisTemplate.opsForValue().setIfAbsent(key, expireTime)
        if (isLock!!) {
            return true
        }
        val currentValue = redisTemplate.opsForValue().get(key) as Long?
        if (currentValue != null && currentValue < System.currentTimeMillis()) {
            val oldValue = redisTemplate.opsForValue().getAndSet(key, expireTime) as Long?
            return oldValue == null || oldValue == currentValue
        }
        return false
    }

    override fun unlock(key: String) {
        val currentValue = redisTemplate.opsForValue().get(key) as Long?
        if (currentValue != null && currentValue > System.currentTimeMillis()) {
            redisTemplate.delete(key)
        }
    }
}
