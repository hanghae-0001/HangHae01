package com.hanghae.health.controller

import com.hanghae.health.dto.ProductDto
import com.hanghae.health.service.ProductService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@Slf4j
@RequestMapping("/products")
@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductDto> {
        val product = productService.getProductById(id);
        return ResponseEntity
            .ok()
            .body(product)
    }

    @PostMapping
    fun addProduct(@RequestBody productInfo: ProductDto): ResponseEntity<ProductDto> {
        val product = productService.addProduct(productInfo)
        return ResponseEntity
            .created(URI("/product/" + product.productId))
            .body(product)
    }
}
