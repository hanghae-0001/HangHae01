package com.hanghae.commerce.user.domain

import com.hanghae.commerce.testconfiguration.UnitTest
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

@UnitTest
class UserTest {

    @Test
    fun createUser() {
        val user = User.createUser(name = "sangmin", age = 20, email = "hanghae001@gmail.com", address = "seoul")

        assertThat(user.name).isEqualTo("sangmin")
        assertThat(user.age).isEqualTo(20)
        assertThat(user.email).isEqualTo("hanghae001@gmail.com")
        assertThat(user.address).isEqualTo("seoul")
    }
}
