package com.hanghae.commerce.cart.presentaion.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class AddCartItemRequest(
    @field:NotBlank(message = "추가할 아이템을 입력해야 합니다.")
    val itemId: String,

    @field:NotNull(message = "userId가 없습니다.")
    val userId: Long ? = null,
)
