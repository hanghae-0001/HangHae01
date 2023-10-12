package com.hanghae.commerce.data.domain.payment

import com.hanghae.commerce.payment.domain.Payment
import com.hanghae.commerce.payment.domain.PaymentRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PaymentEntityRepository(
    private val jpaPaymentRepository: JpaPaymentRepository,
) : PaymentRepository {

    @Transactional
    override fun save(payment: Payment): String {
        return jpaPaymentRepository.save(
            payment.toEntity(),
        ).id!!
    }

}
