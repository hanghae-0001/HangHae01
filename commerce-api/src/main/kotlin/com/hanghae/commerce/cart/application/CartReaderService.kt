package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.domain.*
import com.hanghae.commerce.user.domain.UserReader
import org.springframework.stereotype.Service

@Service
class CartReaderService(
    private val cartReader: CartReader,
    private val cartItemReader: CartItemReader,
    private val userReader: UserReader,
) {

    fun getCartItemsByUserId(userId: Long): List<CartItem> {
        val cart = cartReader.readByUserId(userId) ?: return mutableListOf<CartItem>()
        return cart.id.let { cartItemReader.readByCartId(it) }
    }
}
