package com.hanghae.health.service

import com.hanghae.health.test_configuration.UnitTest
import org.junit.jupiter.api.Test

@UnitTest
class FooServiceTest {

    @Test
    fun tc1(){
        val sut = FooService();
        sut.foo1()
    }
}