package com.hanghae.health.service

import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class FooService {
    val logger = KotlinLogging.logger {}

    fun foo1() {
        logger.info { "executed code" }
        logger.info { "executed code" }
        logger.info { "executed code" }
    }

    fun foo2() {
        logger.info { "executed code" }
        if (1 == 2) {
            logger.info { "do not executed code" }
        }
    }
}
