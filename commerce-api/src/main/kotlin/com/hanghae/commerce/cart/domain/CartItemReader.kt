package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Component

@Component
class CartItemReader(
    private val cartItemRepository: CartItemRepository,
) {

    fun readByCartId(cartId: String): List<CartItem> {
        val cartItems = cartItemRepository.readByCartId(cartId)
        return cartItems.map {
            CartItem(
                id = it.id!!,
                itemId = it.itemId,
                quantity = it.quantity,
                cartId = it.cartId,
            )
        }
    }

    fun readByCartIdAndItemId(cartId: String, itemId: String): CartItem? {
        return cartItemRepository.readByCartIdAndItemId(cartId, itemId)
    }
}
