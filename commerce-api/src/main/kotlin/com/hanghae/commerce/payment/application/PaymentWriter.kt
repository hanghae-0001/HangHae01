package com.hanghae.commerce.payment.application

import com.hanghae.commerce.payment.domain.Payment
import com.hanghae.commerce.payment.domain.PaymentRepository
import org.springframework.stereotype.Component

@Component
class PaymentWriter(
    private val paymentRepository: PaymentRepository,
) {

    fun write(payment: Payment): Payment {
        return paymentRepository.save(payment)
    }
}
