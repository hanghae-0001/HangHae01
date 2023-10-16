package com.hanghae.commerce.order.domain

import com.hanghae.commerce.event.Event

data class OrderCreateEvent(
    val order: Order,
) : Event
