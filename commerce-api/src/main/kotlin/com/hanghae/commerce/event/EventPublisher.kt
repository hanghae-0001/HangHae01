package com.hanghae.commerce.event

interface EventPublisher {
    fun publish(event: Event)
}
