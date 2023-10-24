package com.hanghae.commerce.claim.domain

import com.hanghae.commerce.event.Event
import com.hanghae.commerce.payment.domain.BankAccount

data class OrderCancelCompletedEvent(
    val orderId: String,
    val bankAccount: BankAccount?,
) : Event
