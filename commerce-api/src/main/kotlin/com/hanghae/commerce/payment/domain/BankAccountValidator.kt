package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.payment.infra.PgApiCaller
import org.springframework.stereotype.Component

@Component
class BankAccountValidator(
    private val pgApiCaller: PgApiCaller,
) {
    fun validate(bankAccount: BankAccount): Boolean {
        return pgApiCaller.validateAccount(bankAccount)
    }
}
