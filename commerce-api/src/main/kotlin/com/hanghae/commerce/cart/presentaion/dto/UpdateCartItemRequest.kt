package com.hanghae.commerce.cart.presentaion.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class UpdateCartItemRequest(
    @field:NotBlank(message = "수정할 아이템을 입력해야 합니다.")
    val itemId: String,

    @field:NotNull(message = "userId가 없습니다.")
    val userId: Long ? = null,

    @field:NotNull(message = "수량이 없습니다.")
    @field:Min(1, message = "수량은 1 이상이어야 합니다.")
    val quantity: Int ? = null,
)
