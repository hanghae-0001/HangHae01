package com.hanghae.health.claim.application

import com.hanghae.health.testconfiguration.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
@DisplayName("Given: cancel()")
class OrderCancelServiceTest {

    @Nested
    @DisplayName("When: 주문 취소 요청 시,")
    internal inner class when_order_cancel_request_is_received {

        @Test
        @DisplayName("`Then: 주문이 '결제 대기' 혹은 '발송 대기' 상태가 아니면, IllegalStateException 발생한다.`")
        fun tc1() {
        }
    }

    @Nested
    @DisplayName("When: 주문 취소가 완료되면,")
    internal inner class when_order_cancel_is_completed {

        @Test
        @DisplayName("`Then: '주문 취소' 상태로 변경된다`")
        fun tc1() {
        }

        @Test
        @DisplayName("`Then: 상품의 수량이 복원된다.")
        fun tc2() {
        }

        @Test
        @DisplayName("`Then: 결제가 되어있다면 결제가 취소된다.")
        fun tc3() {
        }
    }
}
