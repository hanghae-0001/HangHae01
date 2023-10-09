package com.hanghae.commerce.order.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemRepository
import com.hanghae.commerce.order.domain.OrderRepository
import com.hanghae.commerce.order.presentaion.dto.OrderCreateRequest
import com.hanghae.commerce.testconfiguration.EnableTestcontainers
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@EnableTestcontainers
@IntegrationTest
class OrderControllerTest(
    @Autowired var orderRepository: OrderRepository,
    @Autowired var itemRepository: ItemRepository,
    @Autowired var objectMapper: ObjectMapper,
    @Autowired var mvc: MockMvc,
) {

    @AfterEach
    fun teardown() {
        orderRepository.deleteAll()
    }

    @Test
    fun `주문 생성`() {
        // given
        persistItem("1", "상품1", 10000, 10)
        persistItem("2", "상품2", 20000, 10)

        // when
        val request = OrderCreateRequest(
            userId = "1",
            itemList = listOf(
                OrderCreateRequest.Item(
                    id = "1",
                    quantity = 1,
                ),
                OrderCreateRequest.Item(
                    id = "2",
                    quantity = 2,
                ),
            ),
        )

        val result: ResultActions = mvc.perform(
            post("/api/orders")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON),
        ).andDo(MockMvcResultHandlers.print())

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.orderId").isString)

        val item1 = itemRepository.findById("1")
        val item2 = itemRepository.findById("2")
        assertThat(item1!!.stock).isEqualTo(9)
        assertThat(item2!!.stock).isEqualTo(8)
    }

    private fun persistItem(
        id: String,
        name: String,
        price: Int,
        stock: Long,
    ) {
        itemRepository.save(
            Item.of(
                id,
                name,
                price,
                stock,
            ),
        )
    }
}
