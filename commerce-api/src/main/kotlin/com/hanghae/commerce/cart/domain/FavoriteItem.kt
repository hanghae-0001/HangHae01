package com.hanghae.commerce.cart.domain

class FavoriteItem(
    val itemId: String,
    var userId: String,
) {
    var id: String? = null

    init {
        if (itemId.isBlank()) {
            throw IllegalArgumentException("상품을 입력해주세요")
        }
    }

    constructor(id: String, itemId: String, userId: String) : this(itemId, userId) {
        this.id = id
    }
}
