package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Repository

@Repository
interface FavoriteItemRepository {
    fun add(favoriteItem: FavoriteItem): FavoriteItem?
    fun readByUserId(userId: String): List<FavoriteItem>
    fun deleteByUserIdAndItemId(userId: String, itemId: String)
    fun readByUserIdAndItemId(userId: String, itemId: String): FavoriteItem?
}
