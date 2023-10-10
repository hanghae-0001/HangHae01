package com.hanghae.commerce.data.domain.store

import com.hanghae.commerce.store.domain.Store

fun Store.toEntity(): StoreEntity {
    return StoreEntity(
        identifier = this.id,
        name = this.name,
        userId = this.userId,
    )
}
