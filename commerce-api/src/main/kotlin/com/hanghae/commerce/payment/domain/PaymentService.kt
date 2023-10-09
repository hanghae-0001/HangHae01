package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.payment.domain.command.PaymentCommand
import org.springframework.stereotype.Service

@Service
class PaymentService {
    fun payment(
        paymentCommand: PaymentCommand,
    ): String {
        TODO()
    }

}
