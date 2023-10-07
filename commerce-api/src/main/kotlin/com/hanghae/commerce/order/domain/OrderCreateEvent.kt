package com.hanghae.commerce.order.domain

import org.springframework.context.ApplicationEvent

class OrderCreateEvent private constructor(
    val order: Order,
) : ApplicationEvent(order) {

    companion object {
        fun from(order: Order): OrderCreateEvent {
            return OrderCreateEvent(order)
        }
    }
}
