package com.hanghae.commerce.payment.domain

interface PaymentRepository {
    fun save(payment: Payment): Payment
    fun findById(id: Long): Payment?
}
