package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Component

@Component
class FavoriteItemWriter(
    private val favoriteItemRepository: FavoriteItemRepository,
) {

    fun addItem(favoriteItem: FavoriteItem): FavoriteItem? {
        return favoriteItemRepository.add(favoriteItem)
    }
}
