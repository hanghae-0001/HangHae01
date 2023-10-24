package com.hanghae.commerce.claim.domain

import com.hanghae.commerce.claim.domain.command.OrderCancelCommand
import com.hanghae.commerce.event.CommerceEventPublisher
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderStatus
import com.hanghae.commerce.order.domain.OrderWriter
import com.hanghae.commerce.testconfiguration.UnitTest
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
@DisplayName("Given: cancel()")
class OrderCancelServiceTest {

    @MockK(relaxed = true)
    private lateinit var orderWriter: OrderWriter

    @MockK(relaxed = true)
    private lateinit var eventPublisher: CommerceEventPublisher

    @InjectMockKs
    private lateinit var sut: OrderCancelService

    @Nested
    @DisplayName("When: 주문 취소가 성공하면")
    inner class Context1 {

        val testOrderId = "testOrderId"
        private lateinit var order: Order
        private lateinit var command: OrderCancelCommand

        @BeforeEach
        fun setup() {
            order = createOrder(orderId = testOrderId, orderStatus = OrderStatus.ORDER_COMPLETE)
            command = createOrderCancelCommand(orderId = testOrderId)
        }

        @Test
        @DisplayName("Then: 주문은 ORDER_CANCEL 상태가 된다.")
        fun tc1() {
            // when
            sut.cancel(command, order)

            // then
            assertThat(order.status).isEqualTo(OrderStatus.ORDER_CANCEL)
        }

        @Test
        @DisplayName("Then: 사유를 반드시 저장한다.")
        fun tc2() {
            // when
            sut.cancel(command, order)

            // then
            assertThat(order.cancelReason).isNotBlank()
        }

        @Test
        @DisplayName("Then: 변경사항을 저장소에 저장한다.")
        fun tc3() {
            // when
            sut.cancel(command, order)

            // then
            verify(exactly = 1) { orderWriter.write(order) }
        }

        @Test
        @DisplayName("Then: OrderCancelCompletedEvent 를 발행한다.")
        fun tc4() {
            // when
            sut.cancel(command, order)

            // then
            val argSlot = slot<OrderCancelCompletedEvent>()
            verify(exactly = 1) { eventPublisher.publish(capture(argSlot)) }

            val capturedEvent = argSlot.captured
            assertThat(capturedEvent.orderId).isEqualTo(testOrderId)
            assertThat(capturedEvent.bankAccount).isNull()
        }

        private fun createOrder(
            orderId: String,
            orderStatus: OrderStatus,
        ) = Order(
            id = orderId,
            userId = "testUserId",
            status = orderStatus,
            orderItemList = listOf(),
            orderAmount = 9356,
            discountAmount = 8638,
            paymentAmount = 5989,
            deliveryFee = 6056,
        )

        private fun createOrderCancelCommand(
            orderId: String,
        ): OrderCancelCommand {
            return OrderCancelCommand(
                orderId = orderId,
                userId = "testUserId",
                reason = "testOrderCancelReason",
            )
        }
    }
}
