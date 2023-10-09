package com.hanghae.commerce.store.presentation.dto

import com.hanghae.commerce.store.domain.Store

data class GetStoreResponse(
    val id: String,
    val name: String,
    val userId: String,
) {
    companion object {
        fun of(store: Store): GetStoreResponse {
            return GetStoreResponse(
                id = store.id,
                name = store.name,
                userId = store.userId,
            )
        }
    }
}
