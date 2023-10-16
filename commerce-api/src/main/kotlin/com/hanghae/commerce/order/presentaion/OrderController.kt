package com.hanghae.commerce.order.presentaion

import com.hanghae.commerce.order.application.OrderCreateFacade
import com.hanghae.commerce.order.presentaion.dto.OrderCreateRequest
import com.hanghae.commerce.order.presentaion.dto.OrderCreateResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderCreateFacade: OrderCreateFacade,
) {
    @PostMapping("")
    fun create(
        @RequestBody request: OrderCreateRequest,
    ): ResponseEntity<OrderCreateResponse> {
        return ResponseEntity.ok(
            orderCreateFacade.create(request),
        )
    }
}
