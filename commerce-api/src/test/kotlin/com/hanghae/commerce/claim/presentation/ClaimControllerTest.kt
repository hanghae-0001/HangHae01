package com.hanghae.commerce.claim.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.hanghae.commerce.claim.presentation.dto.OrderCancelRequest
import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemRepository
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.domain.OrderRepository
import com.hanghae.commerce.order.domain.OrderStatus
import com.hanghae.commerce.payment.domain.BankAccount
import com.hanghae.commerce.testconfiguration.EnableTestcontainers
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@EnableTestcontainers
@IntegrationTest
class ClaimControllerTest {

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mvc: MockMvc

    @AfterEach
    fun teardown() {
        orderRepository.deleteAll()
        itemRepository.deleteAll()
    }

    @Test
    fun `주문 취소`() {
        // given
        val userId = "testUserId"
        val itemId = "testItemId"
        val orderId = "testOrderId"
        val item = persistItem(itemId, "상품1", 10000, 10, "1")
        persistOrder(userId, orderId, item)

        // when
        val request = OrderCancelRequest(
            orderId = orderId,
            userId = userId,
            reason = "단순 변심",
            bankAccount = BankAccount(
                bankName = "수협 은행",
                accountNumber = "testAccountNumber",
                accountHolder = "testAccountHolder",
            ),
        )

        val result: ResultActions = mvc.perform(
            MockMvcRequestBuilders.post("/api/claims/cancel")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON),
        ).andDo(MockMvcResultHandlers.print())

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$").isString)
    }

    private fun persistOrder(
        userId: String,
        orderId: String,
        item: Item,
    ): Order {
        return orderRepository.save(
            Order(
                id = orderId,
                userId = userId,
                orderAmount = 1852,
                discountAmount = 7317,
                paymentAmount = 7738,
                deliveryFee = 6485,
                cancelReason = null,
                status = OrderStatus.PAYMENT_WAIT,
                orderItemList = listOf(
                    OrderItem(
                        id = "testOrderItemId",
                        itemId = item.id,
                        orderId = orderId,
                        name = item.name,
                        price = item.price,
                        quantity = 1,
                    ),
                ),
            ),
        )
    }

    private fun persistItem(
        id: String,
        name: String,
        price: Int,
        stock: Long,
        storeId: String,
    ): Item {
        return itemRepository.save(
            Item.of(
                id,
                name,
                price,
                stock,
                storeId,
            ),
        )
    }
}
