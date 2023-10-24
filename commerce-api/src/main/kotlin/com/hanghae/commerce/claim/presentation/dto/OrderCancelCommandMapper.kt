package com.hanghae.commerce.claim.presentation.dto

import com.hanghae.commerce.claim.domain.command.OrderCancelCommand

fun OrderCancelRequest.toCommand(): OrderCancelCommand {
    return OrderCancelCommand(
        orderId = this.orderId,
        userId = this.userId,
        reason = this.reason,
        bankAccount = this.bankAccount,
    )
}
