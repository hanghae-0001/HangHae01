package com.hanghae.commerce.store.presentation

import com.hanghae.commerce.store.application.StoreCreateService
import com.hanghae.commerce.store.application.StoreReadService
import com.hanghae.commerce.store.presentation.dto.*
import org.springframework.web.bind.annotation.*

@RestController
class StoreController(
    private val storeCreateService: StoreCreateService,
    private val storeReadService: StoreReadService,
) {
    @PostMapping("/store")
    fun createStore(@RequestBody request: CreateStoreRequest): CreateStoreResponse {
        return storeCreateService.createStore(request)
    }

    @GetMapping("/store")
    fun getStore(@RequestBody request: GetStoreRequest): GetStoreResponse {
        return storeReadService.getStore(request.storeId)
    }

    @GetMapping("/stores")
    fun getStoresByUserId(@RequestBody request: GetStoresByUserIdRequest): List<GetStoresByUserIdResponse> {
        return storeReadService.getStoresByUserId(request.userId)
    }
}
