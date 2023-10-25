package com.hanghae.commerce.payment.domain.command

import com.hanghae.commerce.payment.domain.BankAccount

data class PaymentCommand(
    val orderId: String,
    val bankAccount: BankAccount,
)
