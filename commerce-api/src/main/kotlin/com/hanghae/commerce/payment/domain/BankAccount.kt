package com.hanghae.commerce.payment.domain

data class BankAccount(
    val bankName: String,
    val accountNumber: String,
    val accountHolder: String,
)
