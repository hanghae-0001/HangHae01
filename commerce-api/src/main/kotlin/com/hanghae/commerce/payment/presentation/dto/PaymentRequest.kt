package com.hanghae.commerce.payment.presentation.dto

import jakarta.validation.constraints.NotBlank

data class PaymentRequest(
    @field: NotBlank(message = "주문 ID는 필수입니다.")
    val orderId: String,
    @field: NotBlank(message = "카드 번호는 필수입니다.")
    val cardNumber: String
)
