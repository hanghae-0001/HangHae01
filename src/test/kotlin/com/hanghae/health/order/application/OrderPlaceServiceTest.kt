package com.hanghae.health.order.application

import com.hanghae.health.testconfiguration.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
@DisplayName("Given: placeOrder()")
class OrderPlaceServiceTest {
    @Nested
    @DisplayName("When: 발주 요청 시,")
    internal inner class when_place_order_request_is_received {
        @Test
        @DisplayName("Then: 주문이 '배송 대기' 상태가 아니면, IllegalStateException 발생한다.")
        fun tc1() {
        }
    }

    @Nested
    @DisplayName("When: 발주 요청이 완료되면,")
    internal inner class when_place_order_request_is_completed {
        @Test
        @DisplayName("Then: 주문이 '배송 시작' 상태로 변경된다.")
        fun tc1() {
        }
    }
}
