package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Component

@Component
class CartItemWriter(
    private val cartItemRepository: CartItemRepository,
) {

    fun addCartItem(cartItem: CartItem): CartItem? {
        return cartItemRepository.add(cartItem)
    }

    fun updateCartItem(cartItem: CartItem): CartItem? {
        return cartItemRepository.save(cartItem)
    }
}
