package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.domain.*
import com.hanghae.commerce.user.domain.UserReader
import org.springframework.stereotype.Service

@Service
class FavoriteItemReaderService(
    private val favoriteItemReader: FavoriteItemReader,
    private val favoriteItemWriter: FavoriteItemWriter,
    private val userReader: UserReader,
) {

    fun getFavoriteItemsByUserId(userId: String): List<FavoriteItem> {
        return favoriteItemReader.readByUserId(userId)
    }

    fun getFavoriteItemsByUserIdAndItemId(userId: String, itemId: String): FavoriteItem? {
        return favoriteItemReader.readByUserIdAndItemId(userId, itemId)
    }
}
