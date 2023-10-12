package com.hanghae.commerce.data.domain.cart

import com.hanghae.commerce.cart.domain.FavoriteItem
import com.hanghae.commerce.cart.domain.FavoriteItemRepository
import org.springframework.stereotype.Repository

@Repository
class FavoriteItemEntityRepository(
    private val jpaFavoriteItemRepository: JpaFavoriteItemRepository,
) : FavoriteItemRepository {
    override fun add(favoriteItem: FavoriteItem): FavoriteItem? {
        return jpaFavoriteItemRepository.save(FavoriteItemEntity.from(favoriteItem)).let {
            FavoriteItem(id = it.identifier, itemId = it.itemId, userId = it.userId)
        }
    }

    override fun readByUserId(userId: Long): List<FavoriteItem> {
        return jpaFavoriteItemRepository.findByUserId(userId).map {
            FavoriteItem(id = it.identifier, itemId = it.itemId, userId = it.userId)
        }
    }

    override fun deleteByUserIdAndItemId(userId: Long, itemId: String) {
        TODO("Not yet implemented")
    }

    override fun readByUserIdAndItemId(userId: Long, itemId: String): FavoriteItem? {
        return jpaFavoriteItemRepository.findByUserIdAndItemId(userId, itemId)?.let {
            FavoriteItem(id = it.identifier, itemId = it.itemId, userId = it.userId)
        }
    }
}
