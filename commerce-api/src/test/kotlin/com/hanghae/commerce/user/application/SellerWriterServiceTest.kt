package com.hanghae.commerce.user.application

import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.user.domain.UserRepository
import com.hanghae.commerce.user.presentation.dto.CreateSellerRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class SellerWriterServiceTest(
    @Autowired
    private val sellerWriterService: SellerWriterService,
    @Autowired
    private val userRepository: UserRepository,
) {

    @AfterEach
    fun tearDown() {
        userRepository.allDelete()
    }

    @Test
    fun createSeller() {
        // given
        val request = CreateSellerRequest(
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
        )

        // when
        val result = sellerWriterService.createSeller(request)

        // then
        Assertions.assertThat(result.id).isNotNull
    }
}
