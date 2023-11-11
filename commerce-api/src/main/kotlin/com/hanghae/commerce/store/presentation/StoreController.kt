package com.hanghae.commerce.store.presentation

import com.hanghae.commerce.store.application.StoreCreateService
import com.hanghae.commerce.store.application.StoreReadService
import com.hanghae.commerce.store.presentation.dto.*
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class StoreController(
    private val storeCreateService: StoreCreateService,
    private val storeReadService: StoreReadService,
) {
    @PostMapping("/store")
    fun createStore(@AuthenticationPrincipal user: User, @RequestBody request: CreateStoreRequest): CreateStoreResponse {
        return storeCreateService.createStore(user.username, request)
    }

    @GetMapping("/store")
    fun getStore(@AuthenticationPrincipal user: User, @RequestBody request: GetStoreRequest): GetStoreResponse {
        return storeReadService.getStore(user.username, request.storeId)
    }

    @GetMapping("/stores")
    fun getStoresByUserId(@AuthenticationPrincipal user: User): List<GetStoresByUserIdResponse> {
        return storeReadService.getStoresByUserId(user.username)
    }
}
