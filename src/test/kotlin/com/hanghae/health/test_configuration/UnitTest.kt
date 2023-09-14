package com.hanghae.health.test_configuration

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
annotation class UnitTest()
