package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.domain.*
import com.hanghae.commerce.cart.presentaion.dto.AddCartItemRequest
import com.hanghae.commerce.user.domain.UserReader
import org.springframework.stereotype.Service

@Service
class CartWriterService(
    private val cartWriter: CartWriter,
    private val cartItemWriter: CartItemWriter,
    private val cartReader: CartReader,
    private val userReader: UserReader,
) {

    fun addCartItem(request: AddCartItemRequest): CartItem? {
        if (request.userId == null) throw IllegalArgumentException("존재하지 않는 user입니다.")
        val cart = cartReader.readByUserId(request.userId!!) ?: cartWriter.add(request.userId)
        return cartItemWriter.addCartItem(CartItem(cartId = cart?.id!!, itemId = request.itemId))
    }
}
