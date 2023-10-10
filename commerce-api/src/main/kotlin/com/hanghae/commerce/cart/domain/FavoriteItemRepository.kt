package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Repository

@Repository
interface FavoriteItemRepository {
    fun add(favoriteItem: FavoriteItem): FavoriteItem?
    fun readByUserId(userId: Long): List<FavoriteItem>
    fun deleteByUserIdAndItemId(userId: Long, itemId: String)
    fun readByUserIdAndItemId(userId: Long, itemId: String): FavoriteItem?
}
