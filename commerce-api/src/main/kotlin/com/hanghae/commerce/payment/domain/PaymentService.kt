package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.payment.application.PaymentWriter
import com.hanghae.commerce.payment.domain.command.PaymentCommand
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class PaymentService(
    private val paymentWriter: PaymentWriter,
) {
    //    @CircuitBreaker(name = "payment", fallbackMethod = "circuitBreakerFallback")
//    @RateLimiter(name = "payment")
    fun payment(
        paymentCommand: PaymentCommand,
    ): String {
        val order = paymentCommand.order
        if (order.status.isPaymentWait().not()) {
            throw IllegalStateException("이미 결제가 완료된 주문입니다.")
        }
        val payment = Payment(
            orderId = order.id,
            paymentAmount = order.paymentAmount,
        )

        val payement = paymentWriter.write(payment)
        return payement.id
    }

    fun circuitBreakerFallback(e: Exception): String {
        return "Circuit Breaker Fallback"
    }

    fun retryFallback(e: Exception): String {
        return "Retry Fallback"
    }
}
