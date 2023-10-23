package com.hanghae.commerce.payment.domain.command

import com.hanghae.commerce.payment.domain.BankAccount
import com.hanghae.commerce.payment.domain.Payment

data class PaymentRefundCommand(
    val orderId: String,
    val payment: Payment,
    val bankAccount: BankAccount?,
)
