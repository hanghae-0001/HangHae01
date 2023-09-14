package com.hanghae.health.service

import com.hanghae.health.testconfiguration.UnitTest
import org.junit.jupiter.api.Test

@UnitTest
class FooServiceTest {

    @Test
    fun tc1() {
        val sut = FooService()
        sut.foo1()
    }
}
