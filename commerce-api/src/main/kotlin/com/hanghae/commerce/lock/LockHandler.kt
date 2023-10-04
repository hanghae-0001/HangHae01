package com.hanghae.commerce.lock

import org.springframework.stereotype.Component

@Component
class LockHandler(
    private val lockRepository: LockRepository,
) {

    fun requireLock(
        key: String,
        timeout: Long = 1000,
        runnable: () -> Unit,
    ) {
        if (lockRepository.lock(key, timeout)) {
            try {
                runnable()
            } finally {
                lockRepository.unlock(key)
            }
        }
    }
}
