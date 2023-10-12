package com.hanghae.commerce.payment.application.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.domain.OrderRepository
import com.hanghae.commerce.order.domain.OrderStatus
import com.hanghae.commerce.payment.presentation.dto.PaymentRequest
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@IntegrationTest
@DisplayName("결제 API 테스트")
class PaymentServiceTest(
    @Autowired var orderRepository: OrderRepository,
) {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var mvc: MockMvc

     @Test
     fun `결제 요청을 한다`() {
         // given
         val savedOrder = `주문 생성`()
         val request = `주문 결제 요청 생성`(savedOrder)

         // when
         val result: ResultActions = mvc.perform(
             post("/payments")
                 .content(objectMapper.writeValueAsString(request))
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON),
         ).andDo(MockMvcResultHandlers.print())

         // then
         result.andExpect(status().isOk())
     }

    fun `주문 생성`(): Order {
        val order = Order(
            orderAmount = 1,
            discountAmount = 0,
            paymentAmount = 4500,
            deliveryFee = 0,
            status = OrderStatus.PAYMENT_WAIT,
            orderItemList = listOf(
                OrderItem(
                    id = "1",
                    itemId = "1",
//                    orderId = "2",
                    name = "아이스티",
                    price = 3000,
                    quantity = 1,
                ),
                OrderItem(
                    id = "2",
                    itemId = "3",
//                    orderId = "2",
                    name = "아메리카노",
                    price = 1500,
                    quantity = 1,
                ),
            ),
        )
        return orderRepository.save(order)
    }

     fun `주문 결제 요청 생성`(order: Order): PaymentRequest? {
         val cardNumber = "1234-1234-1234-1234"
         return PaymentRequest(order.id, cardNumber)
     }

 }
