package com.hanghae.commerce.payment.domain

import com.hanghae.commerce.common.IdentifierConstants

class Payment(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val orderId: String,
    val paymentAmount: Int,
    val status: PaymentStatus = PaymentStatus.READY,
)
