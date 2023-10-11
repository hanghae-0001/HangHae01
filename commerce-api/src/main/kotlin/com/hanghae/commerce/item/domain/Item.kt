package com.hanghae.commerce.item.domain

class Item(
    val id: String,
    var name: String,
    var price: Int,
    var stock: Long,
    var storeId: String,
) {
    companion object {

        fun of(
            id: String,
            name: String,
            price: Int,
            stock: Long,
            storeId: String,
        ): Item {
            return Item(
                id = id,
                name = name,
                price = price,
                stock = stock,
                storeId = storeId,
            )
        }
    }
}
