package com.hanghae.health.service

import com.hanghae.health.domain.Product
import com.hanghae.health.dto.ProductDto
import com.hanghae.health.repository.ProductEntityRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ProductService (
    private val productRepository: ProductEntityRepository
){

    fun getProductById(id: Long): Optional<Product> {
        return productRepository.findById(id);
    }

    fun addProduct(productInfo: ProductDto) : ProductDto {
        var product = Product(name = productInfo.name, category = productInfo.category, price = productInfo.price, count = productInfo.count)
        product = productRepository.save(product);
        return ProductDto(productId = product.productId, name = product.name, category = product.category, price = product.price, count = product.count)
    }
}
