package com.hanghae.commerce.order.presentaion

import com.hanghae.commerce.order.application.OrderFacade
import com.hanghae.commerce.order.presentaion.dto.OrderRequest
import com.hanghae.commerce.order.presentaion.dto.OrderResponse
import com.hanghae.commerce.order.presentaion.dto.toCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderFacade: OrderFacade,
) {
    @PostMapping("")
    fun order(
        @RequestBody request: OrderRequest,
    ): ResponseEntity<OrderResponse> {
        return ResponseEntity.ok(
            OrderResponse(
                orderFacade.order(
                    request.toCommand(),
                ),
            ),
        )
    }
}
