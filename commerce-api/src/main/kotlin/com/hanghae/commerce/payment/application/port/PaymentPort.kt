package com.hanghae.commerce.payment.application.port

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.payment.domain.Payment

interface PaymentPort {

    fun getOrder(orderId: String): Order?

    fun pay(totalPrice: Int, cardNumber: String?)

    fun save(payment: Payment?)

}
