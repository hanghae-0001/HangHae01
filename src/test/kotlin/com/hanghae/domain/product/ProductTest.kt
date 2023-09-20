package com.hanghae.domain.product

import com.hanghae.health.domain.product.Category
import com.hanghae.health.domain.product.Product
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductTest {

    @Test
    @DisplayName("상품을 등록한다.")
    fun registerProduct() {
        //given & when
        val product = Product("name", Category.CLOTHES, 0, null)

        //then
        Assertions.assertThat(product.name).isEqualTo("name")
        Assertions.assertThat(product.category).isEqualTo(Category.CLOTHES)
        Assertions.assertThat(product.stockCount).isEqualTo(0)
    }
}
