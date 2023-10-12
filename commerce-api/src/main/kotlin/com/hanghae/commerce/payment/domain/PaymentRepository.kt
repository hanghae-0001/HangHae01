package com.hanghae.commerce.payment.domain

import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository {

    fun save(payment: Payment): String

}
