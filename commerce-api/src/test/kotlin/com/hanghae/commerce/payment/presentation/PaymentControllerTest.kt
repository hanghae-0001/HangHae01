package com.hanghae.commerce.payment.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.hanghae.commerce.order.domain.OrderWriter
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderStatus
import com.hanghae.commerce.payment.domain.BankAccount
import com.hanghae.commerce.payment.domain.PaymentRepository
import com.hanghae.commerce.payment.presentation.dto.PaymentRequest
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@IntegrationTest
class PaymentControllerTest {
    @Autowired
    private lateinit var orderWriter: OrderWriter

    @Autowired
    private lateinit var paymentRepository: PaymentRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mvc: MockMvc

    @AfterEach
    fun tearDown() {
        paymentRepository.deleteAll()
    }

    @Test
    fun payment() {
        // given
        val order = persistOrder()

        // when
        val request = PaymentRequest(
            orderId = order.id,
            bankAccount = BankAccount(
                bankName = "국민은행",
                accountNumber = "1234567890",
                accountHolder = "홍길동",
            ),
        )

        val result: ResultActions = mvc.perform(
            post("/api/payments")
                .content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON),
        ).andDo(print())

        // then
        result.andExpect(status().isOk()).andExpect(jsonPath("$.paymentId").isString)
    }

    private fun persistOrder(): Order {
        return orderWriter.write(
            Order(
                id = "testOrderId",
                userId = "testUserId",
                orderAmount = 30000,
                discountAmount = 0,
                paymentAmount = 32500,
                deliveryFee = 2500,
                status = OrderStatus.PAYMENT_WAIT,
                orderItemList = listOf(),
            ),
        )
    }
}
