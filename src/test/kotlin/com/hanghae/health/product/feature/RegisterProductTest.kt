package com.hanghae.health.product.feature

import com.hanghae.health.common.ApiTest
import com.hanghae.health.common.Scenario
import com.hanghae.health.product.domain.ProductJpaRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class RegisterProductTest : ApiTest() {

    @Autowired private val productRepository: ProductJpaRepository? = null

    @Test
    internal fun `save - 상품을 등록한다`() {
        // when
        Scenario.registerProduct().request()

        // then
        Assertions.assertThat(productRepository!!.findAll()).hasSize(1)
    }

    @Test
    internal fun `findById - 등록된 상품을 조회한다`() {
        // when
        val product = productRepository?.getById(1L)

        // then
        assertEquals(product!!.productNo, 1L)
        assertEquals(product.name, "name")
    }
}
