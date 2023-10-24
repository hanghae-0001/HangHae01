package com.hanghae.commerce.claim.application

import com.hanghae.commerce.claim.domain.OrderCancelService
import com.hanghae.commerce.claim.domain.OrderCancelValidator
import com.hanghae.commerce.claim.domain.command.OrderCancelCommand
import com.hanghae.commerce.order.domain.OrderReader
import org.springframework.stereotype.Component

@Component
class OrderCancelFacade(
    private val orderCancelValidator: OrderCancelValidator,
    private val orderCancelService: OrderCancelService,
    private val orderReader: OrderReader,
) {
    fun cancel(command: OrderCancelCommand) {
        val order = orderReader.read(command.orderId)
        orderCancelValidator.validate(command.bankAccount)
        orderCancelService.cancel(command, order)
    }
}
