package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.presentaion.dto.AddCartItemRequest
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class CartReaderServiceTest(
    @Autowired private val cartReaderService: CartReaderService,
    @Autowired private val cartWriterService: CartWriterService,
) {

    @BeforeEach
    fun setUp() {
        val request = AddCartItemRequest(itemId = "item_id1", userId = 1)
        cartWriterService.addCartItem(request)
    }

    @Test
    @DisplayName("유저 장바구니 목록 조회한다")
    fun getCartItemsByUserId() {
        val len = cartReaderService.getCartItemsByUserId(userId = 1).size

        val request = AddCartItemRequest(itemId = "item_id2", userId = 1)
        cartWriterService.addCartItem(request)

        assertThat(cartReaderService.getCartItemsByUserId(userId = 1).size).isEqualTo(len + 1)
    }
}
