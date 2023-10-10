package com.hanghae.commerce.data.domain.payment

import com.hanghae.commerce.payment.domain.Payment
import org.springframework.stereotype.Repository

@Repository
class PaymentEntityRepository(
    private val jpaPaymentRepository: JpaPaymentRepository,
) {
    fun save(payment: Payment): Payment {
        return jpaPaymentRepository.save(payment.toEntity()).toDomain()
    }

    fun findById(id: String): Payment? {
        return jpaPaymentRepository.findById(id).orElse(null)?.toDomain()
    }
}
