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

    fun getFavoriteItemsByUserId(userId: Long): List<FavoriteItem> {
        return favoriteItemReader.readByUserId(userId)
    }

    fun getFavoriteItemsByUserIdAndItemId(userId: Long, itemId: String): FavoriteItem? {
        return favoriteItemReader.readByUserIdAndItemId(userId, itemId)
    }
}
