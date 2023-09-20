package com.hanghae.health.item.application

import com.hanghae.health.testconfiguration.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
@DisplayName("Given: update()")
class ItemUpdateServiceTest {

    @Nested
    @DisplayName("When: 상품 수정 요청 시, ")
    internal inner class when_store_create_request_is_received {

        @Test
        @DisplayName("Then: 상점 내에서 상품명이 중복되면, IllegalArgumentException 발생한다.")
        fun tc1() {
        }

        @Test
        @DisplayName("Then: 상세 정보에 금칙어가 포함되어 있을 시, IllegalArgumentException 발생한다.")
        fun tc2() {
        }
    }

    @Nested
    @DisplayName("When: 상품 수정 요청 시, ")
    internal inner class when_store_update_is_completed {
        @Test
        @DisplayName("Then: 상품은 '판매 대기' 혹은 '판매중' 상태이다.")
        fun tc1() {
        }

        @Test
        @DisplayName("Then: 상품 수량이 0이면, 상품은 '판매 대기' 상태이다.")
        fun tc2() {
        }
    }
}
