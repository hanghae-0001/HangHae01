package com.hanghae.commerce.payment.presentation.dto

import com.hanghae.commerce.payment.domain.BankAccount

data class PaymentRequest(
    val orderId: String,
    val bankAccount: BankAccount,
)
