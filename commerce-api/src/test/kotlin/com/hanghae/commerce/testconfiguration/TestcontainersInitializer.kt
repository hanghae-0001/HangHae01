package com.hanghae.commerce.testconfiguration

import com.redis.testcontainers.RedisContainer
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.utility.DockerImageName

class TestcontainersInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(ctx: ConfigurableApplicationContext) {
        TestPropertyValues.of(
            "spring.redis.host=" + REDIS_CONTAINER.host,
            "spring.redis.port=" + REDIS_CONTAINER.getMappedPort(6379),
        ).applyTo(ctx.environment)
    }

    companion object {
        private val REDIS_CONTAINER: RedisContainer =
            RedisContainer(DockerImageName.parse("redis:7.0.8-alpine")).withExposedPorts(6379)

        init {
            REDIS_CONTAINER.start()
        }
    }
}
