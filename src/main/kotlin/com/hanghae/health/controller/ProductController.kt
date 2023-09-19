package com.hanghae.health.controller

import com.hanghae.health.dto.ProductDto
import com.hanghae.health.service.ProductService
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*

@Slf4j
@RequestMapping("/product")
@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: String): String {
        return "ok"
    }

    @PostMapping
    fun addProduct(@RequestBody productInfo: ProductDto): String {
        productService.addProduct(productInfo);
        return "ok"
    }
}
