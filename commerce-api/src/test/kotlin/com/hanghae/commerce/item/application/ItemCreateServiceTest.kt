package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.presentaion.dto.CreateItemRequest
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class ItemCreateServiceTest(
    @Autowired
    private val itemCreateService: ItemCreateService,
) {

    @Test
    fun createItem() {
        val request = CreateItemRequest(
            name = "아이템",
            price = 200,
            stock = 20,
            storeId = "1",
        )

        val response = itemCreateService.createItem(request)

        Assertions.assertThat(response.name).isEqualTo("아이템")
        Assertions.assertThat(response.price).isEqualTo(200)
        Assertions.assertThat(response.stock).isEqualTo(20)
        Assertions.assertThat(response.storeId).isEqualTo("1")
    }
}
