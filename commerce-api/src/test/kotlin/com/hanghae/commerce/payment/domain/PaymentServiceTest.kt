package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.order.application.OrderReader
import com.hanghae.commerce.order.application.OrderWriter
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderStatus
import com.hanghae.commerce.payment.domain.command.PaymentCommand
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
@DisplayName("Given: payment()")
class PaymentServiceTest {

    @Autowired
    private lateinit var orderReader: OrderReader

    @Autowired
    private lateinit var orderWriter: OrderWriter

    @Autowired
    private lateinit var sut: PaymentService

    @Nested
    @DisplayName("When: 결제가 완료되면, ")
    internal inner class when_payment_complete {
        @Test
        @DisplayName("Then: 주문상태는 '결제대기' -> '발송대기' 상태로 변경된다.")
        fun tc1() {
            // given
            val order = persistOrder(OrderStatus.PAYMENT_WAIT)

            // when
            val paymentId = sut.payment(
                PaymentCommand(
                    order = order,
                    payInfo = PaymentCommand.PayInfo("card"),
                ),
            )

            // then
            val payedOrder = orderReader.read(order.id)
            assertThat(paymentId).isNotNull()
            assertThat(payedOrder.status).isEqualTo(OrderStatus.DELIVERY_PREPARE)
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

    private fun persistOrder(orderStatus: OrderStatus): Order {
        return orderWriter.write(
            Order(
                id = "testOrderId",
                userId = "testUserId",
                orderAmount = 30000,
                discountAmount = 0,
                paymentAmount = 32500,
                deliveryFee = 2500,
                status = orderStatus,
                orderItemList = listOf(),
            ),
        )
    }
}
