package com.hanghae.health.service

import com.hanghae.health.dto.ProductDto
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ProductService 테스트")
class ProductServiceTest{

    @Autowired
    lateinit var productService: ProductService

    @Test
    fun getProductById() {
    }

    @DisplayName("addProduct는 상품을 저장하여 반환한다.")
    @Test
    fun addProduct() {
        //given
        val productDto = ProductDto(name = "상품 이름", category = "상품 카테고리", price = 1000, count = 100)

        //when
        val newProduct = productService.addProduct(productDto);

        //then
        val findProduct = productService.getProductById(newProduct.productId as Long);
        assertThat(findProduct.name).isEqualTo(newProduct.name)
        assertThat(findProduct.category).isEqualTo(newProduct.category)
    }
}
