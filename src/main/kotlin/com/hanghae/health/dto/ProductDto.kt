package com.hanghae.health.dto

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ProductDto (
    val productId: Long?,
    val name: String,
    val category: String,
    val price: Int,
    val count: Int
)
