package com.hanghae.commerce.item.presentaion

import com.hanghae.commerce.item.application.ItemCreateService
import com.hanghae.commerce.item.application.ItemReadService
import com.hanghae.commerce.item.presentaion.dto.CreateItemRequest
import com.hanghae.commerce.item.presentaion.dto.CreateItemResponse
import com.hanghae.commerce.item.presentaion.dto.GetItemByItemIdResponse
import com.hanghae.commerce.item.presentaion.dto.GetItemsByStoreIdResponse
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(
    private val itemCreateService: ItemCreateService,
    private val itemReadService: ItemReadService,
) {

    @PostMapping("/item")
    fun createStore(@RequestBody request: CreateItemRequest): CreateItemResponse {
        return itemCreateService.createItem(request)
    }

    @GetMapping("/items/{storeId}")
    fun getItems(@PathVariable storeId: String): List<GetItemsByStoreIdResponse> {
        return itemReadService.getItemsByStoreId(storeId)
    }

    @GetMapping("/item/{itemId}")
    fun getItem(@PathVariable itemId: String): GetItemByItemIdResponse {
        return itemReadService.getItemByItemId(itemId)
    }
}
