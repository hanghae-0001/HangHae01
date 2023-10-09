package com.hanghae.commerce.payment.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.hanghae.commerce.payment.presentation.dto.PaymentRequest
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@IntegrationTest
class PaymentControllerTest {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun payment() {
        // given


        // when
//        val request = PaymentRequest()

//        val result: ResultActions = mvc.perform(
//            MockMvcRequestBuilders.post("/api/orders")
//                .content(objectMapper.writeValueAsString(request))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON),
//        ).andDo(MockMvcResultHandlers.print())
//
//        // then
//        result.andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").isString)
//
//        val item1 = itemRepository.findById("1")
//        val item2 = itemRepository.findById("2")
//        Assertions.assertThat(item1!!.stock).isEqualTo(9)
//        Assertions.assertThat(item2!!.stock).isEqualTo(8)
    }
}
