package com.hanghae.commerce.payment.adapter

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.order.domain.OrderRepository
import com.hanghae.commerce.payment.application.port.PaymentPort
import com.hanghae.commerce.payment.domain.Payment
import com.hanghae.commerce.payment.domain.PaymentRepository
import org.springframework.stereotype.Component

@Component
 class PaymentAdapter(
    private val paymentGateway: PaymentGateway,
    private val paymentRepository: PaymentRepository,
    private val orderRepository: OrderRepository,
 ) : PaymentPort {

     override fun getOrder(orderId: String): Order? {
         return orderRepository.findById(orderId)
     }

     override fun pay(totalPrice: Int, cardNumber: String?) {
         paymentGateway.execute(totalPrice, cardNumber);
     }

     override fun save(payment: Payment?) {
         payment?.let { paymentRepository.save(it) };
     }

 }
