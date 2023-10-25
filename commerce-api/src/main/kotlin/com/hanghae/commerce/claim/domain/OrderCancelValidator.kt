package com.hanghae.commerce.claim.domain

import com.hanghae.commerce.payment.domain.BankAccount
import com.hanghae.commerce.payment.domain.BankAccountValidator
import org.springframework.stereotype.Component

@Component
class OrderCancelValidator(
    private val bankAccountValidator: BankAccountValidator,
) {
    fun validate(command: BankAccount?) {
        validateRefundableBankAccount(command)
    }

    private fun validateRefundableBankAccount(command: BankAccount?) {
        if (command == null) {
            return
        }
        require(bankAccountValidator.validate(command)) { "Invalid bank account" }
    }
}
