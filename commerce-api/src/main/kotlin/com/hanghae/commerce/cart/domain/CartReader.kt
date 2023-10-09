package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Component

@Component
class CartReader(
    private val cartRepository: CartRepository,
) {

    fun readByUserId(userId: Long): Cart? {
        return cartRepository.readByUserId(userId)
    }
}
