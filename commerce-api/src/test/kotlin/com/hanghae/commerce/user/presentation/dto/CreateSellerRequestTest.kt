package com.hanghae.commerce.user.presentation.dto

import com.hanghae.commerce.testconfiguration.UnitTest
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class CreateSellerRequestTest {

    @Test
    fun validateName() {
        // given
        val validator = Validation.buildDefaultValidatorFactory().validator
        val request = CreateSellerRequest(
            name = "",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
        )

        // when
        val violations: Set<ConstraintViolation<CreateSellerRequest>> = validator.validate(request)

        val violation: ConstraintViolation<CreateSellerRequest> = violations.stream()
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
        val request = CreateSellerRequest(
            name = "hanghae",
            age = 20,
            email = "",
            address = "seoul",
        )

        // when
        val violations: Set<ConstraintViolation<CreateSellerRequest>> = validator.validate(request)

        val violation: ConstraintViolation<CreateSellerRequest> = violations.stream()
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
        val request = CreateSellerRequest(
            name = "hanghae",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "",
        )

        // when
        val violations: Set<ConstraintViolation<CreateSellerRequest>> = validator.validate(request)

        val violation: ConstraintViolation<CreateSellerRequest> = violations.stream()
            .filter { v -> v.getPropertyPath().toString().equals("address") }
            .findFirst()
            .get()

        // then
        assertThat(violation.getMessage()).isEqualTo("주소를 공백으로 할 수 없습니다.")
    }
}
