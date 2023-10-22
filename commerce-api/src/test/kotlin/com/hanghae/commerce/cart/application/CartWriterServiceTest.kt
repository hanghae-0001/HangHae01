package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.presentaion.dto.AddCartItemRequest
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class CartWriterServiceTest(
    @Autowired private val cartReaderService: CartReaderService,
    @Autowired private val cartWriterService: CartWriterService,
) {

    @Nested
    @DisplayName("장바구니에")
    internal inner class when_a_product_is_added {
        private var itemQuantity: Int? = 0
        private var cartSize: Int? = 0

        @BeforeEach
        fun setUp() {
            val request = AddCartItemRequest(itemId = "item_id1", userId = 1)
            itemQuantity = cartWriterService.addCartItem(request)?.quantity

            cartSize = cartReaderService.getCartItemsByUserId(1).size
        }

        @Test
        @DisplayName("같은 상품을 추가하면 수량+1이 된다.")
        fun addItem() {
            val request = AddCartItemRequest(itemId = "item_id1", userId = 1)
            val cartItem = cartWriterService.addCartItem(request)

            assertThat(cartItem?.quantity).isEqualTo(itemQuantity?.plus(1))
        }

        @Test
        @DisplayName("새로운 상품을 추가하면 추가된다.")
        fun addNewItem() {
            val request = AddCartItemRequest(itemId = "item_id3", userId = 1)
            cartWriterService.addCartItem(request)

            assertThat(cartReaderService.getCartItemsByUserId(1).size).isEqualTo(cartSize?.plus(1))
        }
    }
}
