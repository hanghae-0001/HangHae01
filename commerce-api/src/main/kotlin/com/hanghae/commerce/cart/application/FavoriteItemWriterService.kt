package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.domain.*
import com.hanghae.commerce.cart.presentaion.dto.AddFavoriteItemRequest
import com.hanghae.commerce.user.domain.UserReader
import org.springframework.stereotype.Service

@Service
class FavoriteItemWriterService(
    private val favoriteItemReader: FavoriteItemReader,
    private val favoriteItemWriter: FavoriteItemWriter,
    private val userReader: UserReader,
) {

    fun addFavoriteItem(request: AddFavoriteItemRequest): FavoriteItem? {
        favoriteItemReader.readByUserIdAndItemId(request.userId!!, request.itemId)?.let { throw IllegalArgumentException("이미 즐겨찾기한 상품입니다!") }
        return favoriteItemWriter.addItem(FavoriteItem(itemId = request.itemId, userId = request.userId))
    }
}
