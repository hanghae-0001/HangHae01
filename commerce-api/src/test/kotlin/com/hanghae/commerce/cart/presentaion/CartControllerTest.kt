package com.hanghae.commerce.cart.presentaion

import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@IntegrationTest
@DisplayName("장바구니 API 테스트")
class CartControllerTest(
    @Autowired
    private val mockMvc: MockMvc,
) {

    @BeforeEach
    fun setUp() {
        mockMvc.perform(
            post("/carts/add-item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n" +
                        "    \"itemId\": \"item_id1\",\n" +
                        "    \"userId\": 1\n" +
                        "}",
                ),
        ).andExpect(status().isOk)
    }

    @Test
    @DisplayName("/carts/users/{userId} 는 유저의 장바구니 상품 리스트를 조회한다.")
    fun getCartItemList() {
        val response = mockMvc.perform(
            get("/carts/users/1"),
        ).andReturn()

        Assertions.assertThat(response.response.status).isEqualTo(HttpStatus.OK.value())
    }

    @Nested
    @DisplayName("/carts/add-item 은")
    internal inner class when_a_product_is_added {

        @Test
        @DisplayName("장바구니에 상품을 추가한다.")
        fun inputValidRequest() {
            mockMvc.perform(
                post("/carts/add-item")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n" +
                            "    \"itemId\": \"item_id1\",\n" +
                            "    \"userId\": 2\n" +
                            "}",
                    ),
            ).andExpect(status().isOk)
        }

        @Test
        @DisplayName("상품 정보를 기입하지 않으면 에러가 발생한다.")
        fun inputInvalidItemId() {
            mockMvc.perform(
                post("/carts/add-item")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n" +
                            "    \"userId\": 2\n" +
                            "}",
                    ),
            ).andExpect(status().is4xxClientError)
        }

        @Test
        @DisplayName("유저 정보를 기입하지 않으면 에러가 발생한다.")
        fun inputInvalidUserId() {
            mockMvc.perform(
                post("/carts/add-item")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n" +
                            "    \"itemId\": \"item_id11\"\n" +
                            "}",
                    ),
            ).andExpect(status().is4xxClientError)
        }
    }
}
