package com.hanghae.health.service

import com.hanghae.storage.db.main.user.UserEntityRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FooService(
    private val userEntityRepository: UserEntityRepository,
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun foo1() {
        logger.info("executed code")
        logger.info("executed code")
    }
}
