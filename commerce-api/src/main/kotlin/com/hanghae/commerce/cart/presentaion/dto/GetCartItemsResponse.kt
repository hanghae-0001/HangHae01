package com.hanghae.commerce.cart.presentaion.dto

import com.hanghae.commerce.cart.domain.CartItem

class GetCartItemsResponse(
    var id: String,
    var itemId: String,
    var quantity: Int,
) {
    companion object {
        fun of(cartItem: CartItem): GetCartItemsResponse {
            return GetCartItemsResponse(
                id = cartItem.id!!,
                itemId = cartItem.itemId,
                quantity = cartItem.quantity,
            )
        }

        fun of(cartItems: List<CartItem>): List<GetCartItemsResponse> {
            return cartItems.map { of(it) }
        }
    }
}
