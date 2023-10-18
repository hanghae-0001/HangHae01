package com.hanghae.commerce

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthApplication

val logger = KotlinLogging.logger { }

fun main(args: Array<String>) {
    runApplication<HealthApplication>(*args)
    logger.info { "Complete running application!" }
}
