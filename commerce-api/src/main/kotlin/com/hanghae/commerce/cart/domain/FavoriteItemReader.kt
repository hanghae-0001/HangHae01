package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Component

@Component
class FavoriteItemReader(
    private val favoriteItemRepository: FavoriteItemRepository,
) {
    fun readByUserId(userId: Long): List<FavoriteItem> {
        return favoriteItemRepository.readByUserId(userId)
    }

    fun readByUserIdAndItemId(userId: Long, itemId: String): FavoriteItem? {
        return favoriteItemRepository.readByUserIdAndItemId(userId, itemId)
    }
}
