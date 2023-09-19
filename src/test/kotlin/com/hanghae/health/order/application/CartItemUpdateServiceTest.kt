package com.hanghae.health.order.application

import com.hanghae.health.testconfiguration.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
class CartItemUpdateServiceTest {

    @Nested
    @DisplayName("Given: clear()")
    internal inner class clear {

        @Nested
        @DisplayName("When: 장바구니에 여러 상품이 담겨있을 때")
        internal inner class when_cart_item_is_many {
            @Test
            @DisplayName("Then: 모두 비운다.")
            fun tc1() {
            }
        }
    }

    @Nested
    @DisplayName("Given: updateItemCount()")
    internal inner class updateItemCount {
        @Nested
        @DisplayName("When: 장바구니에 A 상품의 수량이 3개 담겨있을 때, ")
        internal inner class when_cart_item_quantity_is_3 {
            @Test
            @DisplayName("Then: 3개를 더하면, 수량은 6이 된다.")
            fun tc1() {
            }

            @Test
            @DisplayName("Then: 3개를 더했을 떄 재고가 없다면, OutOfStockException 발생한다.")
            fun tc2() {
            }

            @Test
            @DisplayName("Then: 2개를 빼면, 수량은 1이 된다.")
            fun tc3() {
            }
        }
    }
}
