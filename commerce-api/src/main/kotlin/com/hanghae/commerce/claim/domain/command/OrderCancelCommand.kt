package com.hanghae.commerce.claim.domain.command

import com.hanghae.commerce.payment.domain.BankAccount
data class OrderCancelCommand(
    val orderId: String,
    val userId: String,
    val reason: String,
    val bankAccount: BankAccount? = null,
)
