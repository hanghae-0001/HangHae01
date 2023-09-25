package com.hanghae.storage.config

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.hanghae.storage.domain"])
@EnableJpaRepositories(basePackages = ["com.hanghae.storage.domain"])
class MainJpaConfiguration {

    @PostConstruct
    fun init() {
        println("MainJpaConfiguration init")
    }
}
