package com.hanghae.health.order.application

import com.hanghae.health.testconfiguration.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
@DisplayName("Given: add()")
class CartItemRegisterServiceTest {

    @Nested
    @DisplayName("When: A, B 판매자의 상품의 재고가 각각 5개일 떄,")
    internal inner class when_a_and_b_seller_product_stock_is_5 {
        @Test
        @DisplayName("Then: A 상품을 2개씩 2번 담으면, 4개로 합쳐서 담긴다.")
        fun tc1() {
        }

        @Test
        @DisplayName("Then: A 상품을 6개를 담으면, OutOfStockException 발생한다.")
        fun tc2() {
        }

        @Test
        @DisplayName("Then: 존재하지 않는 상품번호를 요청하면, IllegalArgumentException 발생한다.")
        fun tc3() {
        }

        @Test
        @DisplayName("Then: A 상품을 2개, B 상품을 3개 담으면, 정상적으로 담긴다.")
        fun tc4() {
        }
    }
}
