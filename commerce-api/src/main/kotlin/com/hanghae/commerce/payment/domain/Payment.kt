package com.hanghae.commerce.payment.domain

class Payment(
    val id: String,
    val orderId: String,
    val paymentAmount: Int,
    val status: PaymentStatus,
) {
}
