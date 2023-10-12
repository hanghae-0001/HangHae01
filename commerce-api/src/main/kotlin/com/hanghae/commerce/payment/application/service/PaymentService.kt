package com.hanghae.commerce.payment.application.service

import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.payment.application.port.PaymentPort
import com.hanghae.commerce.payment.domain.Payment
import com.hanghae.commerce.payment.presentation.dto.PaymentRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class PaymentService(
    private val paymentPort: PaymentPort,
) {

    @PostMapping
    @Transactional
    fun payment(@RequestBody request: PaymentRequest): ResponseEntity<Void?>? {
        val order: Order? = paymentPort.getOrder(request.orderId)

        if (order != null) {
            println("order >>>>" + order.id)
        }

        val payment = Payment.from(order, request.cardNumber)
        paymentPort.pay(payment.getPrice(), payment.cardNumber)
        paymentPort.save(payment)

        return ResponseEntity.status(HttpStatus.OK).build<Void>()
    }

}
