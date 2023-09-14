package com.hanghae.health.service

import com.hanghae.health.test_configuration.IntegrationTest
import org.junit.jupiter.api.Test

@IntegrationTest
class FooServiceIT {

    @Test
    fun tc1(){
        val sut = FooService();
        sut.foo2()
    }
}