package com.hanghae.commerce.data.domain.payment

import org.springframework.data.jpa.repository.JpaRepository

interface JpaPaymentRepository : JpaRepository<PaymentEntity, Long> {
}
