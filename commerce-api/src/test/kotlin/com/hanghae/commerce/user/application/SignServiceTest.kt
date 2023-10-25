package com.hanghae.commerce.user.application

import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserType
import com.hanghae.commerce.user.domain.UserWriter
import com.hanghae.commerce.user.presentation.dto.SignInRequest
import com.hanghae.commerce.user.presentation.dto.SignUpRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class SignServiceTest @Autowired constructor(
    private val signService: SignService,
    private val userWriter: UserWriter,
) {
    @AfterEach
    fun clear() {
        userWriter.allDelete()
    }

    @Test
    fun 회원가입() {
        // given
        val request = SignUpRequest("colabear754", "1234", "콜라곰", 27, "colabear754@email.com", "seoul", UserType.CUSTOMER)
        // when
        val response = signService.registerUser(request)
        // then
        assertThat(response.account).isEqualTo("colabear754")
        assertThat(response.name).isEqualTo("콜라곰")
        assertThat(response.age).isEqualTo(27)
        assertThat(response.email).isEqualTo("colabear754@email.com")
        assertThat(response.address).isEqualTo("seoul")
        assertThat(response.userType).isEqualTo(UserType.CUSTOMER)
    }

    @Test
    fun `아이디는 중복될 수 없다`() {
        // given
        userWriter.save(User("1", "colabear754","1234","colabear",20,"colabear754@email.com", "seoul", UserType.CUSTOMER))
        val request = SignUpRequest("colabear754","1234","colabear",20,"colabear754@email.com", "seoul", UserType.CUSTOMER)
        // when
        // then
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            signService.registerUser(request)
        }.also { assertThat(it.message).isEqualTo("이미 사용중인 아이디입니다.") }
    }

    @Test
    fun 로그인() {
        // given
        userWriter.save(User("1", "colabear754","1234","colabear",20,"colabear754@email.com", "seoul", UserType.CUSTOMER))
        // when
        val response = signService.signIn(SignInRequest("colabear754", "1234"))
        // then
        assertThat(response.name).isEqualTo("콜라곰")
        assertThat(response.type).isEqualTo(UserType.CUSTOMER)
    }

    @Test
    fun 로그인실패() {
        // given
        userWriter.save(User("1", "colabear754","1234","colabear",20,"colabear754@email.com", "seoul", UserType.CUSTOMER))
        // when
        // then
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            signService.signIn(SignInRequest("colabear754", "4321"))
        }.also { assertThat(it.message).isEqualTo("아이디 또는 비밀번호가 일치하지 않습니다.") }
    }
}
