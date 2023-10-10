package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.common.exception.TooManyRequest
import com.hanghae.commerce.order.application.OrderReader
import com.hanghae.commerce.order.application.OrderWriter
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderStatus
import com.hanghae.commerce.payment.domain.command.PaymentCommand
import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.tools.TestConcurrentExecutor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
@DisplayName("Given: payment()")
class PaymentServiceTest {

    @Autowired
    private lateinit var testConcurrentExecutor: TestConcurrentExecutor

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
            val order = persistOrder("testOrderId", OrderStatus.PAYMENT_WAIT)

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
    @DisplayName("When: 여러 결제 요청이 동시에 발생할 때, ")
    internal inner class when_more_than_300_requests_occur_per_minute {
        @Test
        @DisplayName("Then: 3초당 10개 이상이면, throw TooManyRequest ")
        fun tc1() {
            // given
            val orderList: List<Order> = (1..11).map {
                persistOrder("testOrderId_$it", OrderStatus.PAYMENT_WAIT)
            }

            // when
            val paymentCompleteList: MutableList<String> = mutableListOf()
            val tooManyRequestExList: MutableList<Throwable> = mutableListOf()
            for (order in orderList) {
                try {
                    val paymentId = sut.payment(
                        PaymentCommand(
                            order = order,
                            payInfo = PaymentCommand.PayInfo("card"),
                        ),
                    )
                    paymentCompleteList.add(paymentId)
                } catch (e: TooManyRequest) {
                    tooManyRequestExList.add(e)
                }
            }

            // then
            assertThat(paymentCompleteList).hasSize(10)
            assertThat(tooManyRequestExList).hasSize(1)
        }
    }

    private fun persistOrder(
        orderId: String,
        orderStatus: OrderStatus,
    ): Order {
        return orderWriter.write(
            Order(
                id = orderId,
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
