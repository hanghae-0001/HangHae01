package com.hanghae.commerce.data.domain.payment

import com.hanghae.commerce.data.domain.order.OrderEntity
import com.hanghae.commerce.data.domain.payment.vo.BankAccountVO
import com.hanghae.commerce.payment.domain.BankAccount
import com.hanghae.commerce.payment.domain.Payment

fun Payment.toEntity(): PaymentEntity {
    return PaymentEntity(
        identifier = this.id,
        order = OrderEntity.from(this.orderId),
        paymentAmount = this.paymentAmount,
        bankAccountVO = this.bankAccount.toEntity(),
        status = status,
    )
}

fun BankAccount.toEntity(): BankAccountVO {
    return BankAccountVO(
        bankName = this.bankName,
        accountNumber = this.accountNumber,
        accountHolder = this.accountHolder,
    )
}

fun PaymentEntity.toDomain(): Payment {
    return Payment(
        id = this.id,
        orderId = this.order.id,
        paymentAmount = this.paymentAmount,
        bankAccount = this.bankAccountVO.toDomain(),
        status = this.status,
    )
}

private fun BankAccountVO.toDomain(): BankAccount {
    return BankAccount(
        bankName = this.bankName,
        accountNumber = this.accountNumber,
        accountHolder = this.accountHolder,
    )
}
