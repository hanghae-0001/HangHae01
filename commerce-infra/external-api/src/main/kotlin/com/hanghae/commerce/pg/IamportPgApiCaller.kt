package com.hanghae.commerce.pg

import com.hanghae.commerce.payment.domain.BankAccount
import com.hanghae.commerce.payment.domain.Payment
import com.hanghae.commerce.payment.infra.PgApiCaller
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class IamportPgApiCaller() : PgApiCaller {

    val logger = KotlinLogging.logger { }
    override fun payment(payment: Payment) {
        Thread.sleep(300)
        logger.info { "[PAYMENT] Success payment - {orderId:${payment.orderId}}" }
    }

    override fun refund(payment: Payment) {
        Thread.sleep(300)
        logger.info { "[PAYMENT] Success refund - {orderId:${payment.orderId}}" }
    }

    override fun validateAccount(bankAccount: BankAccount): Boolean {
        if (bankAccount.bankName == "하나은행") {
            return false
        }
        return true
    }
}
