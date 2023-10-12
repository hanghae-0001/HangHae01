package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.common.IdentifierConstants
import com.hanghae.commerce.order.domain.Order

class Payment(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val order: Order,
    val cardNumber: String,
) {

    companion object {
        fun from(order: Order?, cardNumber: String): Payment {
            return Payment(
                order = order!!,
                cardNumber = cardNumber,
            )
        }
    }

    fun getPrice(): Int {
        return order.paymentAmount
    }

}
