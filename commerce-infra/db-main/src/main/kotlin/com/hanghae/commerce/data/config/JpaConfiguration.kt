package com.hanghae.commerce.data.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan(basePackages = ["com.hanghae.commerce.data.domain"])
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.hanghae.commerce.data.domain"])
class JpaConfiguration
