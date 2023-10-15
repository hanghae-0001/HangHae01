package com.hanghae.commerce.data.domain.item

import com.hanghae.commerce.item.domain.Item

fun Item.toEntity(): ItemEntity {
    return ItemEntity(
        identifier = this.id,
        name = this.name,
        price = this.price,
        stock = this.stock,
        storeId = this.storeId,
    )
}

fun ItemEntity.toDomain(): Item {
    return Item(
        id = this.id,
        name = this.name,
        price = this.price,
        stock = this.stock,
        storeId = this.storeId,
    )
}
