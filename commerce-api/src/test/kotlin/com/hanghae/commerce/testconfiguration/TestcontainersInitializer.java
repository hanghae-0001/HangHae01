package com.hanghae.commerce.testconfiguration;

import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.utility.DockerImageName;

public class TestcontainersInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final RedisContainer REDIS_CONTAINER = new RedisContainer(DockerImageName.parse("redis:7.0.8-alpine")).withExposedPorts(6379);

    static {
        REDIS_CONTAINER.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
        TestPropertyValues.of(
            "spring.redis.host=" + REDIS_CONTAINER.getHost(),
            "spring.redis.port=" + REDIS_CONTAINER.getMappedPort(6379)
        ).applyTo(ctx.getEnvironment());
    }
}
