package com.hanghae.health

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthApplication

fun main(args: Array<String>) {
    runApplication<HealthApplication>(*args)
}
