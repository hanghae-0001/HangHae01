package com.hanghae.commerce.cart.domain

class FavoriteItem(
    val itemId: String,
    var userId: Long? = null,
) {
    var id: String? = null

    init {
        if (userId == null) {
            throw IllegalArgumentException("유저id를 입력해주세요")
        }
        if (itemId.isBlank()) {
            throw IllegalArgumentException("상품을 입력해주세요")
        }
    }

    constructor(id: String, itemId: String, userId: Long?) : this(itemId, userId) {
        this.id = id
    }
}
