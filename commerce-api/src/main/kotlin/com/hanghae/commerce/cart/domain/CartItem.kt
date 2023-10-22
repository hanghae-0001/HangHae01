package com.hanghae.commerce.cart.domain

class CartItem(
    val cartId: String,
    val itemId: String,
    var id: String? = null,
    var userId: String = "",
    var quantity: Int = 0,
) {

    init {
        if (cartId.isBlank()) {
            throw IllegalArgumentException("장바구니를 입력해주세요")
        }
        if (itemId.isBlank()) {
            throw IllegalArgumentException("상품을 입력해주세요")
        }
    }

    constructor(id: String, itemId: String, quantity: Int, cartId: String) : this(cartId, itemId) {
        this.quantity = quantity
        this.id = id
    }

    constructor(cartId: String, userId: String, itemId: String) : this(cartId, itemId) {
        this.userId = userId
    }

    constructor(userId: String, itemId: String) : this(userId, itemId, id = "") {
        this.userId = userId
    }
}
