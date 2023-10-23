package com.hanghae.commerce.payment.domain.command

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.payment.domain.BankAccount

data class PaymentCommand(
    val order: Order,
    val bankAccount: BankAccount,
)
