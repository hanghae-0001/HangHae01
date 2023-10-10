package com.hanghae.commerce.data.domain.cart

import org.springframework.data.jpa.repository.JpaRepository

interface JpaFavoriteItemRepository : JpaRepository<FavoriteItemEntity, String> {
    fun findByUserId(userId: Long): List<FavoriteItemEntity>
    fun findByUserIdAndItemId(userId: Long, itemId: String): FavoriteItemEntity?
    fun deleteByUserIdAndItemId(userId: Long, itemId: String)
}
