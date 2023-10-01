package com.hanghae.commerce.user.domain

import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired


@IntegrationTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserRepositoryTest (
    @Autowired
    private val userRepository: UserRepository,
){

    @Test
    @Order(1)
    fun save() {
        //given
        val user = User.createUser(name = "sangmin", age = 20, email = "hanghae001@gmail.com", address = "seoul", UserType.CUSTOMER)

        //when
        val userId = userRepository.save(user)

        //then
        Assertions.assertThat(userId).isEqualTo(1L)
    }

    @Test
    @Order(2)
    fun read() {
        //given
        val user = User.createUser(name = "sangmin", age = 20, email = "hanghae001@gmail.com", address = "seoul", UserType.CUSTOMER)
        val userId = userRepository.save(user)

        //when
        val savedUser = userRepository.read(userId)

        //then
        Assertions.assertThat(savedUser!!.name).isEqualTo("sangmin")
        Assertions.assertThat(savedUser!!.age).isEqualTo(20)
        Assertions.assertThat(savedUser!!.email).isEqualTo("hanghae001@gmail.com")
        Assertions.assertThat(savedUser!!.address).isEqualTo("seoul")
        Assertions.assertThat(savedUser!!.userType).isEqualTo(UserType.CUSTOMER)
    }
}
