package com.hanghae.health.service

import com.hanghae.health.domain.Product
import com.hanghae.health.dto.ProductDto
import com.hanghae.health.repository.ProductEntityRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.util.Optional

@Service
class ProductService (
    private val productRepository: ProductEntityRepository
){

    @Transactional(readOnly = true)
    fun getProductById(id: Long): ProductDto {
        val product = productRepository.findById(id).orElseThrow { throw IllegalArgumentException("찾는 상품이 없습니다!") }
        return ProductDto(productId = product.productId, name = product.name, category = product.category, price = product.price, count = product.count)
    }

    @Transactional
    fun addProduct(productInfo: ProductDto) : ProductDto {
        var product = Product(name = productInfo.name, category = productInfo.category, price = productInfo.price, count = productInfo.count)
        product = productRepository.save(product);
        return ProductDto(productId = product.productId, name = product.name, category = product.category, price = product.price, count = product.count)
    }
}
