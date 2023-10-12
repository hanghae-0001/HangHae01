package com.hanghae.commerce.user.domain

import com.hanghae.commerce.testconfiguration.UnitTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class UserTest {

    @Test
    fun createUser() {
        val user = User(
            id = "1",
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
            userType = UserType.CUSTOMER,
        )

        assertThat(user.id).isEqualTo("1")
        assertThat(user.name).isEqualTo("sangmin")
        assertThat(user.age).isEqualTo(20)
        assertThat(user.email).isEqualTo("hanghae001@gmail.com")
        assertThat(user.address).isEqualTo("seoul")
    }

    @Test
    fun createUserWithBlankName() {
        // then
        Assertions.assertThatThrownBy {
            User(
                id = "1",
                name = "",
                age = 20,
                email = "hanghae001@gmail.com",
                address = "seoul",
                userType = UserType.CUSTOMER,
            )
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이름은 비어 있을 수 없습니다")
    }

    @Test
    fun createUserWithBlankEmail() {
        //then
        Assertions.assertThatThrownBy {
            User(
                id = "1",
                name = "sangmin",
                age = 20,
                email = "",
                address = "seoul",
                userType = UserType.CUSTOMER,
            )
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이메일은 비어 있을 수 없습니다")
    }

    @Test
    fun createUserWithBlankAddress() {
        // then
        Assertions.assertThatThrownBy {
            User(
                id = "1",
                name = "sangmin",
                age = 20,
                email = "hanghae001@gmail.com",
                address = "",
                userType = UserType.CUSTOMER,
            )
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("주소는 비어 있을 수 없습니다")
    }
}
