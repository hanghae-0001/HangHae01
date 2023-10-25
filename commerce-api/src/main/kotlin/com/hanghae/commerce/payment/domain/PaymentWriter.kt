package com.hanghae.commerce.payment.domain

import org.springframework.stereotype.Component

@Component
class PaymentWriter(
    private val paymentRepository: PaymentRepository,
) {

    fun write(payment: Payment): Payment {
        return paymentRepository.save(payment)
    }
}
