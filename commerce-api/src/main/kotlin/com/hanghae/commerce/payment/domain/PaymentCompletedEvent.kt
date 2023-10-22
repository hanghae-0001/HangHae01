package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.event.Event

data class PaymentCompletedEvent(val orderId: String) : Event
