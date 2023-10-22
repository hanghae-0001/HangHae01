package com.hanghae.commerce.cart.presentaion.dto

import jakarta.validation.constraints.NotBlank

class AddCartItemRequest(
    @field:NotBlank(message = "추가할 아이템을 입력해야 합니다.")
    val itemId: String,

    @field:NotBlank(message = "유저 id가 없습니다.")
    val userId: String,
) {
//    fun toDomain(request: AddCartItemRequest): CartItem {
//        return CartItem(userId = request.userId, itemId = request.itemId)
//    }
}
