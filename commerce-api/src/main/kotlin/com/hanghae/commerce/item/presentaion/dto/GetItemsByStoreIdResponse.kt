package com.hanghae.commerce.item.presentaion.dto

import com.hanghae.commerce.item.domain.Item

data class GetItemsByStoreIdResponse(
    val id: String,
    val storeId: String,
    val name: String,
    val price: Int,
    val stock: Long,
) {
    companion object {
        fun listOf(items: List<Item>): List<GetItemsByStoreIdResponse> {
            return items.map { item ->
                GetItemsByStoreIdResponse(
                    id = item.id,
                    storeId = item.storeId,
                    name = item.name,
                    price = item.price,
                    stock = item.stock,
                )
            }
        }
    }
}
