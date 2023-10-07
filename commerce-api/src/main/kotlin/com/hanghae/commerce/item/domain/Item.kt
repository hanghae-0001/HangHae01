package com.hanghae.commerce.item.domain

class Item(
    val id: String,
    var name: String,
    var price: Int,
    var stock: Long,
) {
    companion object {

        fun of(
            id: String,
            name: String,
            price: Int,
            stock: Long,
        ): Item {
            return Item(
                id = id,
                name = name,
                price = price,
                stock = stock,
            )
        }
    }
}
