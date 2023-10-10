package com.hanghae.commerce.payment.domain.command

import com.hanghae.commerce.order.domain.Order

class PaymentCommand(
    val order: Order,
    val payInfo: PayInfo,
) {
    data class PayInfo(
        val payMethod: String,
    )
}
