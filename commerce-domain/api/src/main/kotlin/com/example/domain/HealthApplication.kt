package com.example.domain

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example"])
class HealthApplication

fun main(args: Array<String>) {
    runApplication<HealthApplication>(*args)
}
