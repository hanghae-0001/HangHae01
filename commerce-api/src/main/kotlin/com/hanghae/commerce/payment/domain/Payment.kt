package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.common.IdentifierConstants

class Payment(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val orderId: String,
    val paymentAmount: Int,
    var bankAccount: BankAccount,
    var status: PaymentStatus = PaymentStatus.READY,
) {
    fun cancel() {
        this.status = PaymentStatus.CANCEL
    }

    fun isPaid(): Boolean {
        return this.status == PaymentStatus.PAID
    }

    fun refund(bankAccount: BankAccount?) {
        this.status = PaymentStatus.REFUND
        bankAccount?.let {
            this.bankAccount = bankAccount
        }
    }
}
