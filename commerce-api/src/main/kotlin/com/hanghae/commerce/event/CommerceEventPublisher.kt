package com.hanghae.commerce.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class CommerceEventPublisher(
    private val eventPublisher: ApplicationEventPublisher,
) : EventPublisher {
    override fun publish(event: Event) {
        eventPublisher.publishEvent(event)
    }
}
