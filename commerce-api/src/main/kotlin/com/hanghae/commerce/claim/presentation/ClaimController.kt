package com.hanghae.commerce.claim.presentation

import com.hanghae.commerce.claim.application.OrderCancelFacade
import com.hanghae.commerce.claim.presentation.dto.OrderCancelRequest
import com.hanghae.commerce.claim.presentation.dto.toCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/claims")
class ClaimController(
    private val claimFacade: OrderCancelFacade,
) {
    @PostMapping("/cancel")
    fun cancel(
        @RequestBody request: OrderCancelRequest,
    ): ResponseEntity<String> {
        claimFacade.cancel(request.toCommand())
        return ResponseEntity.ok("ok")
    }
}
