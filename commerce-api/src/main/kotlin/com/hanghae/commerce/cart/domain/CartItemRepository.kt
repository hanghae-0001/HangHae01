package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository {
    fun add(cartItem: CartItem): CartItem
    fun save(cartItem: CartItem): CartItem
    fun readByCartId(cartId: String): List<CartItem>
    fun readByCartIdAndItemId(cartId: String, itemId: String): CartItem?
}
