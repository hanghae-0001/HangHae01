package com.hanghae.commerce.item.presentaion.dto

data class CreateItemRequest(
    val storeId: String,
    val name: String,
    val price: Int,
    val stock: Long,
)
