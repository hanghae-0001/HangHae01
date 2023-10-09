package com.hanghae.commerce.payment.domain.command

class PaymentCommand(
    val orderId: String,
    val payInfo: PayInfo,
) {
    data class PayInfo(
        val payMethod: String,
    )
}
