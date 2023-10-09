package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.testconfiguration.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
@DisplayName("Given: payment()")
class PaymentServiceTest {

    @Nested
    @DisplayName("When: 결제가 완료되면, ")
    internal inner class when_payment_complete {
        @Test
        @DisplayName("Then: 주문상태는 '결제대기' -> '발송대기' 상태로 변경된다.")
        fun tc1() {
        }
    }

    @Nested
    @DisplayName("When: 실패율이 5% 이상이 되면, ")
    internal inner class when_more_than_300_requests_occur_per_minute {
        @Test
        @DisplayName("Then: Circuit Breaker가 발동한다.")
        fun tc1() {
        }
    }
}
