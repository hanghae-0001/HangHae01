package com.hanghae.commerce.item.presentaion

import com.hanghae.commerce.item.application.ItemCreateService
import com.hanghae.commerce.item.application.ItemReadService
import com.hanghae.commerce.item.presentaion.dto.CreateItemRequest
import com.hanghae.commerce.item.presentaion.dto.CreateItemResponse
import com.hanghae.commerce.item.presentaion.dto.GetItemByItemIdResponse
import com.hanghae.commerce.item.presentaion.dto.GetItemsByStoreIdResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(
    private val itemCreateService: ItemCreateService,
    private val itemReadService: ItemReadService,
) {

    @PostMapping("/item")
    fun createStore(@AuthenticationPrincipal user: User, @RequestBody request: CreateItemRequest): CreateItemResponse {
        return itemCreateService.createItem(user.username, request)
    }

    @GetMapping("/items/{storeId}")
    fun getItems(@AuthenticationPrincipal user: User, @PathVariable storeId: String): List<GetItemsByStoreIdResponse> {
        return itemReadService.getItems(user.username, storeId)
    }

    @GetMapping("/item/{itemId}")
    fun getItem(@AuthenticationPrincipal user: User, @PathVariable itemId: String): GetItemByItemIdResponse {
        return itemReadService.getItem(user.username, itemId)
    }
}
