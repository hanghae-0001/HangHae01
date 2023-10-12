package com.hanghae.commerce.cart.presentaion.dto

import com.hanghae.commerce.cart.domain.FavoriteItem

class GetFavoriteItemResponse(
    var id: String,
    var itemId: String,
) {
    companion object {
        fun of(favoriteItem: FavoriteItem): GetFavoriteItemResponse {
            return GetFavoriteItemResponse(
                id = favoriteItem.id!!,
                itemId = favoriteItem.itemId,
            )
        }

        fun of(cartItems: List<FavoriteItem>): List<GetFavoriteItemResponse> {
            return cartItems.map { of(it) }
        }
    }
}
