package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Component

@Component
class CartWriter(
    private val cartRepository: CartRepository,
) {
    fun add(userId: String): Cart {
        return cartRepository.add(userId)
    }
}
