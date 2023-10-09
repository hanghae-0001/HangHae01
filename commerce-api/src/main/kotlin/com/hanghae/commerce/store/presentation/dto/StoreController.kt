package com.hanghae.commerce.store.presentation.dto

import com.hanghae.commerce.store.application.StoreCreateService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StoreController (
    private val storeCreateService: StoreCreateService,
){
    @PostMapping("/store")
    fun createStore(@RequestBody request: CreateStoreRequest) : CreateStoreResponse{
        return storeCreateService.createStore(request)
    }
}
