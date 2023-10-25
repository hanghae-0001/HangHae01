package com.hanghae.commerce.data.domain.payment.vo

import jakarta.persistence.Embeddable

@Embeddable
data class BankAccountVO(
    val bankName: String,
    val accountNumber: String,
    val accountHolder: String,
)
