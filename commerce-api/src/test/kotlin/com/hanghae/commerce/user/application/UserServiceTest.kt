package com.hanghae.commerce.user.application

import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.user.domain.UserRepository
import com.hanghae.commerce.user.presentation.dto.CreateUserRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class UserServiceTest (
    @Autowired
    private val userService: UserService,
    @Autowired
    private val userRepository: UserRepository,
){

    @AfterEach
    fun tearDown() {
        userRepository.allDelete()
    }

    @Test
    fun createUser() {
        //given
        val request = CreateUserRequest(name = "sangmin", age = 20, email = "hanghae001@gmail.com", address = "seoul")

        //when
        val result = userService.createUser(request)

        //then
        Assertions.assertThat(result.id).isEqualTo(1L)
    }

    @Test
    fun getUserById() {
        //given
        val request = CreateUserRequest(name = "sangmin", age = 20, email = "hanghae001@gmail.com", address = "seoul")
        val createUser = userService.createUser(request)

        //when
        val result = userService.getUserById(createUser.id)

        //then
        Assertions.assertThat(result.id).isEqualTo(createUser.id)
        Assertions.assertThat(result.name).isEqualTo(request.name)
        Assertions.assertThat(result.age).isEqualTo(request.age)
        Assertions.assertThat(result.email).isEqualTo(request.email)
        Assertions.assertThat(result.address).isEqualTo(request.address)
    }
}
