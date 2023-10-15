package com.hanghae.commerce.item.presentaion.dto

import com.hanghae.commerce.item.domain.Item

data class GetItemByItemIdResponse(
    val id: String,
    val name: String,
    val price: Int,
    val stock: Long,
) {
    companion object {
        fun of(item: Item): GetItemByItemIdResponse {
            return GetItemByItemIdResponse(
                id = item.id,
                name = item.name,
                price = item.price,
                stock = item.stock,
            )
        }
    }
}
