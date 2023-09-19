package com.hanghae.health.payment.application

import com.hanghae.health.testconfiguration.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
@DisplayName("Given: payment()")
class PaymentServiceTest {

    @Nested
    @DisplayName("When: 결제 요청 시, ")
    internal inner class when_payment_request_is_received {

        @Test
        @DisplayName("Then: 주문이 '결제 대기' 상태가 아니면, IllegalStateException 발생한다.")
        fun tc1() {
        }
    }

    @Nested
    @DisplayName("When: 결제가 완료되면,")
    internal inner class when_payment_is_completed {

        @Test
        @DisplayName("`Then: 주문 '배송 대기' 상태로 변경된다.")
        fun tc1() {
        }
    }
}
