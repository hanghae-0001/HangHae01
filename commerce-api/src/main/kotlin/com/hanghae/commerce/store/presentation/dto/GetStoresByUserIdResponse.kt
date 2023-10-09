package com.hanghae.commerce.store.presentation.dto

import com.hanghae.commerce.store.domain.Store

data class GetStoresByUserIdResponse(
    val id: String,
    val name: String,
    val userId: String,
) {
    companion object {
        fun listOf(stores: List<Store>): List<GetStoresByUserIdResponse> {
            return stores.map { store ->
                GetStoresByUserIdResponse(
                    id = store.id,
                    name = store.name,
                    userId = store.userId,
                )
            }
        }
    }
}
