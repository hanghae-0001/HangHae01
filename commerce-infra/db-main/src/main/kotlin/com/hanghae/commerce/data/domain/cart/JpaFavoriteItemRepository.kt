package com.hanghae.commerce.data.domain.cart

import org.springframework.data.jpa.repository.JpaRepository

interface JpaFavoriteItemRepository : JpaRepository<FavoriteItemEntity, String> {
    fun findByUserId(userId: String): List<FavoriteItemEntity>
    fun findByUserIdAndItemId(userId: String, itemId: String): FavoriteItemEntity?
    fun deleteByUserIdAndItemId(userId: String, itemId: String)
}
