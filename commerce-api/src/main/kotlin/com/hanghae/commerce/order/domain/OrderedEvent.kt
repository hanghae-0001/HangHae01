package com.hanghae.commerce.order.domain

import com.hanghae.commerce.event.Event

data class OrderedEvent(
    val order: Order,
) : Event
