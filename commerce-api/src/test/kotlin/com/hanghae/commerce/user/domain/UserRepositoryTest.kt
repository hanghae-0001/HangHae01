package com.hanghae.commerce.user.domain

import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class UserRepositoryTest(
    @Autowired
    private val userRepository: UserRepository,
) {
    @Test
    fun save() {
        // given
        val user = User(
            id = "1",
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
            UserType.CUSTOMER,
        )

        // when
        val savedUser = userRepository.save(user)

        // then
        Assertions.assertThat(savedUser.id).isEqualTo("1")
        Assertions.assertThat(savedUser.name).isEqualTo("sangmin")
        Assertions.assertThat(savedUser.age).isEqualTo(20)
        Assertions.assertThat(savedUser.email).isEqualTo("hanghae001@gmail.com")
        Assertions.assertThat(savedUser.address).isEqualTo("seoul")
        Assertions.assertThat(savedUser.userType).isEqualTo(UserType.CUSTOMER)
    }

    @Test
    fun findById() {
        // given
        val user = User(
            id = "2",
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
            UserType.CUSTOMER,
        )
        val savedUser = userRepository.save(user)

        // when
        val foundUser = userRepository.findById(savedUser.id)!!

        // then
        Assertions.assertThat(foundUser.id).isEqualTo("2")
        Assertions.assertThat(foundUser.name).isEqualTo("sangmin")
        Assertions.assertThat(foundUser.age).isEqualTo(20)
        Assertions.assertThat(foundUser.email).isEqualTo("hanghae001@gmail.com")
        Assertions.assertThat(foundUser.address).isEqualTo("seoul")
        Assertions.assertThat(foundUser.userType).isEqualTo(UserType.CUSTOMER)
    }
}
