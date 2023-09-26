package com.example.domain.data.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan(basePackages = ["com.example.domain.data"])
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.example.domain.data"])
class JpaConfiguration
