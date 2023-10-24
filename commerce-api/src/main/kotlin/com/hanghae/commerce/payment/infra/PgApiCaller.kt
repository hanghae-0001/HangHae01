package com.hanghae.commerce.payment.infra

import com.hanghae.commerce.payment.domain.BankAccount
import com.hanghae.commerce.payment.domain.Payment

interface PgApiCaller {

    fun payment(payment: Payment)
    fun refund(payment: Payment)
    fun validateAccount(bankAccount: BankAccount): Boolean
}
