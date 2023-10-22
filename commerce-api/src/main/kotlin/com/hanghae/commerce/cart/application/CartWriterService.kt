package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.domain.*
import com.hanghae.commerce.cart.presentaion.dto.UpdateCartItemRequest
import org.springframework.stereotype.Service

@Service
class CartWriterService(
    private val cartWriter: CartWriter,
    private val cartItemWriter: CartItemWriter,
    private val cartReader: CartReader,
    private val cartItemReader: CartItemReader,
) {
    fun addCart(userId: String): String {
        val cart = cartWriter.add(userId)
        return cart.id
    }

    fun addCartItem(cartId: String, itemId: String): CartItem {
        return cartItemWriter.addCartItem(CartItem(cartId = cartId, itemId = itemId))
    }

    fun updateCartItemQuantity(request: UpdateCartItemRequest): CartItem? {
        val cart = cartReader.readByUserId(request.userId) ?: throw IllegalArgumentException("장바구니 정보가 없습니다")
        val cartItem = cartItemReader.readByCartIdAndItemId(cart.id, request.itemId) ?: throw IllegalArgumentException("장바구니에 해당 상품의 정보가 없습니다.")

        cartItem.quantity = request.quantity!!
        return cartItemWriter.updateCartItem(cartItem)
    }

    private fun validateUserId(userId: String?) {
        if (userId == null) {
            throw IllegalArgumentException("존재하지 않는 user입니다.")
        }
    }
}
