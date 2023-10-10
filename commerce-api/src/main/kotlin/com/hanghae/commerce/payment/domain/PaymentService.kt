package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.common.exception.TooManyRequest
import com.hanghae.commerce.event.CommerceEventPublisher
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.payment.application.PaymentWriter
import com.hanghae.commerce.payment.domain.command.PaymentCommand
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class PaymentService(
    private val paymentWriter: PaymentWriter,
    private val commerceEventPublisher: CommerceEventPublisher,
) {
    //    @CircuitBreaker(name = "payment", fallbackMethod = "circuitBreakerFallback")
    @RateLimiter(name = "payment", fallbackMethod = "rateLimiterFallback")
    fun payment(
        paymentCommand: PaymentCommand,
    ): String {
        val order = paymentCommand.order
        if (!orderAvailableForPayment(order)) {
            throw IllegalStateException("이미 결제가 완료된 주문입니다.")
        }

        val savedPayment = paymentWriter.write(
            Payment(
                orderId = order.id,
                paymentAmount = order.paymentAmount,
            ),
        )
        commerceEventPublisher.publish(PaymentCompletedEvent(order.id))
        return savedPayment.id
    }

    private fun orderAvailableForPayment(order: Order) = order.status.isPaymentWait()

    fun circuitBreakerFallback(e: Exception): String {
        return "Circuit Breaker Fallback"
    }

    fun rateLimiterFallback(e: Exception): String {
        throw TooManyRequest()
    }

    fun retryFallback(e: Exception): String {
        return "Retry Fallback"
    }
}
