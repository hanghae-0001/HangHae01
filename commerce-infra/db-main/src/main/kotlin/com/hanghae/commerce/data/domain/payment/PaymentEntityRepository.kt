package com.hanghae.commerce.data.domain.payment

import com.hanghae.commerce.payment.domain.Payment
import com.hanghae.commerce.payment.domain.PaymentRepository
import org.springframework.stereotype.Repository

@Repository
class PaymentEntityRepository(
    private val jpaPaymentRepository: JpaPaymentRepository,
) : PaymentRepository {

    override fun save(payment: Payment): Payment {
        return jpaPaymentRepository.save(payment.toEntity()).toDomain()
    }

    override fun findById(id: String): Payment? {
        return jpaPaymentRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findByOrderId(orderId: String): Payment? {
        return jpaPaymentRepository.findByOrderId(orderId)?.toDomain()
    }

    override fun deleteAll() {
        jpaPaymentRepository.deleteAll()
    }
}
