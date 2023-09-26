package com.sample.data.config

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan(basePackages = ["com.sample.data.domain"])
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.sample.data.domain"])
class JpaConfiguration {

    @PostConstruct
    fun init() {
        println("JpaConfiguration init")
    }
}
