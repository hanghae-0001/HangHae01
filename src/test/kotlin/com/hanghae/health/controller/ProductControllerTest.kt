package com.hanghae.health.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ProductController 테스트")
class ProductControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun addProductWithValidAttributes() {
        mockMvc.perform(
            post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                    "    \"name\": \"JPA 책\",\n" +
                    "    \"category\": \"도서\",\n" +
                    "    \"price\": 10000,\n" +
                    "    \"count\": 10\n" +
                    "}"
                )
        ).andExpect(status().isCreated)
    }

    @Test
    fun addProductWithInvalidAttributes() {
        mockMvc.perform(
            post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                    "    \"name\": \"JPA 책\",\n" +
                    "    \"category\": 1000,\n" +
                    "    \"price\": \"잘못된 가격\",\n" +
                    "    \"count\": 10\n" +
                    "}"
                )
        ).andExpect(status().is4xxClientError)
    }
}
