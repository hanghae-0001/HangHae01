package com.hanghae.commerce.payment.presentation.dto

import com.hanghae.commerce.payment.domain.command.PaymentCommand

data class PaymentRequest(
    val orderId: String,
    val payInfo: PaymentCommand.PayInfo,
)
