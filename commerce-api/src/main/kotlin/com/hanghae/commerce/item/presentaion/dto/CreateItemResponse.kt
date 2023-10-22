package com.hanghae.commerce.item.presentaion.dto

import com.hanghae.commerce.item.domain.Item

data class CreateItemResponse(
    val id: String,
    val storeId: String,
    val name: String,
    val price: Int,
    val stock: Long,
) {
    companion object {
        fun of(item: Item): CreateItemResponse {
            return CreateItemResponse(
                id = item.id,
                storeId = item.storeId,
                name = item.name,
                price = item.price,
                stock = item.stock,
            )
        }
    }
}
