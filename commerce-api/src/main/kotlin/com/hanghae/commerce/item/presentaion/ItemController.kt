package com.hanghae.commerce.item.presentaion

import com.hanghae.commerce.item.application.ItemCreateService
import com.hanghae.commerce.item.presentaion.dto.CreateItemRequest
import com.hanghae.commerce.item.presentaion.dto.CreateItemResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
     private val itemCreateService: ItemCreateService,
) {

    @PostMapping("/item")
    fun createStore(@RequestBody request: CreateItemRequest): CreateItemResponse {
        return itemCreateService.createItem(request)
    }
}
