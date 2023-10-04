package com.hanghae.commerce.user.presentation.dto

import com.hanghae.commerce.testconfiguration.UnitTest
import com.hanghae.commerce.user.domain.UserType
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class CreateUserRequestTest {

    @Test
    fun validateName() {

        // given
        val validator = Validation.buildDefaultValidatorFactory().validator
        val request = CreateUserRequest(
            name = "",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
            userType = UserType.CUSTOMER,
        )

        // when
        val violations: Set<ConstraintViolation<CreateUserRequest>> = validator.validate(request)

        val violation: ConstraintViolation<CreateUserRequest> = violations.stream()
            .filter { v -> v.getPropertyPath().toString().equals("name") }
            .findFirst()
            .get()

        // then
        assertThat(violation.getMessage()).isEqualTo("이름을 공백으로 할 수 없습니다.")
    }

    @Test
    fun validateEmail() {

        // given
        val validator = Validation.buildDefaultValidatorFactory().validator
        val request = CreateUserRequest(
            name = "hanghae",
            age = 20,
            email = "",
            address = "seoul",
            userType = UserType.CUSTOMER,
        )

        // when
        val violations: Set<ConstraintViolation<CreateUserRequest>> = validator.validate(request)

        val violation: ConstraintViolation<CreateUserRequest> = violations.stream()
            .filter { v -> v.getPropertyPath().toString().equals("email") }
            .findFirst()
            .get()

        // then
        assertThat(violation.getMessage()).isEqualTo("이메일을 공백으로 할 수 없습니다.")
    }

    @Test
    fun validateAddress() {

        // given
        val validator = Validation.buildDefaultValidatorFactory().validator
        val request = CreateUserRequest(
            name = "hanghae",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "",
            userType = UserType.CUSTOMER,
        )

        // when
        val violations: Set<ConstraintViolation<CreateUserRequest>> = validator.validate(request)

        val violation: ConstraintViolation<CreateUserRequest> = violations.stream()
            .filter { v -> v.getPropertyPath().toString().equals("address") }
            .findFirst()
            .get()

        // then
        assertThat(violation.getMessage()).isEqualTo("주소를 공백으로 할 수 없습니다.")
    }
}
