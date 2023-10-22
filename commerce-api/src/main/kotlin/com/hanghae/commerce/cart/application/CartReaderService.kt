package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.domain.*
import org.springframework.stereotype.Service

@Service
class CartReaderService(
    private val cartReader: CartReader,
    private val cartItemReader: CartItemReader,
) {

    fun getCartItemsByUserId(userId: String): List<CartItem> {
        val cart = cartReader.readByUserId(userId) ?: return mutableListOf<CartItem>()
        return cart.id.let { cartItemReader.readByCartId(it) }
    }

    fun getCartByUserId(userId: String): String? {
        val cart = cartReader.readByUserId(userId)
        return cart?.id
    }
}
