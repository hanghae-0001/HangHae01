package com.hanghae.commerce.user.presentation

import com.hanghae.commerce.user.application.SellerWriterService
import com.hanghae.commerce.user.presentation.dto.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seller")
class SellerController(
    private val sellerService: SellerWriterService,
) {

    @PostMapping
    fun createSeller(@RequestBody createSellerRequest: CreateSellerRequest): CreateSellerResponse {
        return sellerService.createSeller(createSellerRequest)
    }
}
