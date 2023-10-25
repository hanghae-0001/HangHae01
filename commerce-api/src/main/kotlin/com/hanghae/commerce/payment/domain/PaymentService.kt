package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.event.CommerceEventPublisher
import com.hanghae.commerce.order.domain.Order
import com.hanghae.commerce.payment.domain.command.PaymentCommand
import com.hanghae.commerce.payment.domain.command.PaymentRefundCommand
import com.hanghae.commerce.payment.infra.PgApiCaller
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val pgApiCaller: PgApiCaller,
    private val paymentWriter: PaymentWriter,
    private val commerceEventPublisher: CommerceEventPublisher,
) {

    @CircuitBreaker(name = "payment", fallbackMethod = "circuitBreakerFallback")
    fun payment(
        command: PaymentCommand,
        order: Order,
    ): String {
        if (!order.isPaymentWait()) {
            throw IllegalStateException("이미 결제가 완료된 주문입니다.")
        }

        val payment = paymentWriter.write(
            Payment(
                orderId = order.id,
                paymentAmount = order.paymentAmount,
                bankAccount = command.bankAccount,
            ),
        )
        pgApiCaller.payment(payment)
        commerceEventPublisher.publish(PaymentCompletedEvent(order.id))
        return payment.id
    }

    fun circuitBreakerFallback(e: Exception): String {
        return "Circuit Breaker Fallback"
    }

    fun refund(command: PaymentRefundCommand, order: Order) {
        val payment = command.payment
        if (!payment.isPaid()) {
            throw IllegalStateException("환불이 불가능한 주문입니다.")
        }

        payment.refund(command.bankAccount)
        paymentWriter.write(payment)
        pgApiCaller.refund(payment)
    }
}
