package com.hanghae.commerce.payment.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.hanghae.commerce.order.domain.OrderCreateEvent
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderCreateEventListener(
    private val objectMapper: ObjectMapper,
    private val paymentService: PaymentService,
) {
    val logger = KotlinLogging.logger {}

    @EventListener
    fun sendPush(event: OrderCreateEvent) {
        paymentService.payment(event.order)
    }
}
