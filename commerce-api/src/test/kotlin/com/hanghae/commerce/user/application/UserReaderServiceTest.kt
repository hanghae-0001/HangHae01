package com.hanghae.commerce.user.application

import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.user.domain.UserRepository
import com.hanghae.commerce.user.domain.UserType
import com.hanghae.commerce.user.presentation.dto.CreateCustomerRequest
import com.hanghae.commerce.user.presentation.dto.CreateSellerRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class UserReaderServiceTest(
    @Autowired
    private val userReaderService: UserReaderService,
    @Autowired
    private val customerWriterService: CustomerWriterService,
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
    fun getCustomerUserById() {
        // given
        val request = CreateCustomerRequest(
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
        )

        val createdCustomer = customerWriterService.createCustomer(request)

        // when
        val result = userReaderService.getUserById(createdCustomer.id)

        // then
        Assertions.assertThat(result.id).isEqualTo(createdCustomer.id)
        Assertions.assertThat(result.name).isEqualTo(request.name)
        Assertions.assertThat(result.age).isEqualTo(request.age)
        Assertions.assertThat(result.email).isEqualTo(request.email)
        Assertions.assertThat(result.address).isEqualTo(request.address)
        Assertions.assertThat(result.userType).isEqualTo(UserType.CUSTOMER)
    }

    @Test
    fun getSellerUserById() {
        // given
        val request = CreateSellerRequest(
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
        )

        val createdSeller = sellerWriterService.createSeller(request)

        // when
        val result = userReaderService.getUserById(createdSeller.id)

        // then
        Assertions.assertThat(result.id).isEqualTo(createdSeller.id)
        Assertions.assertThat(result.name).isEqualTo(request.name)
        Assertions.assertThat(result.age).isEqualTo(request.age)
        Assertions.assertThat(result.email).isEqualTo(request.email)
        Assertions.assertThat(result.address).isEqualTo(request.address)
        Assertions.assertThat(result.userType).isEqualTo(UserType.SELLER)
    }
}
