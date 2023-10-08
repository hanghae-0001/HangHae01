package com.hanghae.commerce.item.domain

import com.hanghae.commerce.common.IdentifierConstants

class Item(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
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
