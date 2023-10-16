package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.domain.*
import com.hanghae.commerce.cart.presentaion.dto.AddCartItemRequest
import com.hanghae.commerce.cart.presentaion.dto.UpdateCartItemRequest
import org.springframework.stereotype.Service

@Service
class CartWriterService(
    private val cartWriter: CartWriter,
    private val cartItemWriter: CartItemWriter,
    private val cartReader: CartReader,
    private val cartItemReader: CartItemReader,
) {

    fun addCartItem(request: AddCartItemRequest): CartItem? {
        validateUserId(request.userId!!)
        val cart = cartReader.readByUserId(request.userId) ?: cartWriter.add(request.userId)
        return cartItemWriter.addCartItem(CartItem(cartId = cart?.id!!, itemId = request.itemId))
    }

    fun updateCartItemQuantity(request: UpdateCartItemRequest): CartItem? {
        val cart = cartReader.readByUserId(request.userId!!) ?: throw IllegalArgumentException("장바구니 정보가 없습니다")
        val cartItem = cartItemReader.readByCartIdAndItemId(cart.id, request.itemId) ?: throw IllegalArgumentException("장바구니에 해당 상품의 정보가 없습니다.")
//        CartItem(cartId = it.id!!, itemId = it.itemId, quantity = request.quantity!!)
        cartItem.quantity = request.quantity!!
        return cartItemWriter.updateCartItem(cartItem)
    }

    private fun validateUserId(userId: Long?) {
        if (userId == null) {
            throw IllegalArgumentException("존재하지 않는 user입니다.")
        }
    }
}
