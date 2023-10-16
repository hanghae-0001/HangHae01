package com.hanghae.commerce.cart.domain

class CartItem(
    val cartId: String,
    val itemId: String,
    var quantity: Int = 0,
) {
    var id: String? = null

    init {
        if (cartId.isBlank()) {
            throw IllegalArgumentException("장바구니를 입력해주세요")
        }
        if (itemId.isBlank()) {
            throw IllegalArgumentException("상품을 입력해주세요")
        }
    }

    constructor(id: String, cartId: String, itemId: String, quantity: Int) : this(cartId, itemId) {
        this.id = id
        this.quantity = quantity
    }
}
