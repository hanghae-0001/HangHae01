package com.hanghae.commerce.payment.adapter

interface PaymentGateway {
    fun execute(totalPrice: Int, cardNumber: String?)
}
