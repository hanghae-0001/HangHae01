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
        val user = User.createUser(
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
            UserType.CUSTOMER,
        )

        // when
        val userId = userRepository.save(user)

        // then
        Assertions.assertThat(userId).isNotNull
    }

    @Test
    fun read() {
        // given
        val user = User.createUser(
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
            UserType.CUSTOMER,
        )
        val userId = userRepository.save(user)

        // when
        val savedUser = userRepository.read(userId)

        // then
        Assertions.assertThat(savedUser!!.name).isEqualTo("sangmin")
        Assertions.assertThat(savedUser!!.age).isEqualTo(20)
        Assertions.assertThat(savedUser!!.email).isEqualTo("hanghae001@gmail.com")
        Assertions.assertThat(savedUser!!.address).isEqualTo("seoul")
        Assertions.assertThat(savedUser!!.userType).isEqualTo(UserType.CUSTOMER)
    }
}
