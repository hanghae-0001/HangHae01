package com.hanghae.commerce.item.presentaion

import com.hanghae.commerce.item.application.ItemCreateService
import com.hanghae.commerce.item.application.ItemReadService
import com.hanghae.commerce.item.presentaion.dto.CreateItemRequest
import com.hanghae.commerce.item.presentaion.dto.CreateItemResponse
import com.hanghae.commerce.item.presentaion.dto.GetItemsByStoreIdResponse
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(
     private val itemCreateService: ItemCreateService,
     private val itemReadService: ItemReadService
) {

    @PostMapping("/item")
    fun createStore(@RequestBody request: CreateItemRequest): CreateItemResponse {
        return itemCreateService.createItem(request)
    }

    @GetMapping("/items/{storeId}")
    fun createStore(@PathVariable storeId: String): List<GetItemsByStoreIdResponse> {
        return itemReadService.getItemsByStoreId(storeId)
    }
}
