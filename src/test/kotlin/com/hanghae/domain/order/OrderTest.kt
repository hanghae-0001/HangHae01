package com.hanghae.domain.order

import com.hanghae.health.domain.order.Order
import com.hanghae.health.domain.product.Product
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OrderTest {
    @Test
    @DisplayName("상품 3개 주문한다")
    fun makeOrderWithThreeProduct() {
        val product = Product.fixture()
        val order = Order(null, mutableListOf(), null)
        order.addProduct(product)
        order.addProduct(product)
        order.addProduct(product)

        // then
        Assertions.assertThat(order.products).hasSize(3)
    }
}
