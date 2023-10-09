package com.hanghae.commerce.order.domain

import com.hanghae.commerce.event.Event

class OrderCreateEvent private constructor(
    val order: Order,
) : Event {

    companion object {
        fun from(order: Order): OrderCreateEvent {
            return OrderCreateEvent(order)
        }
    }
}
