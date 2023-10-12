package com.hanghae.commerce.data.domain.cart

import com.hanghae.commerce.cart.domain.FavoriteItem
import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "favorite_item",
    uniqueConstraints = [UniqueConstraint(name = "UniqueItemAndUser", columnNames = ["item_id", "user_id"])],
)
class FavoriteItemEntity(
    @Id val identifier: String,

    @Column(name = "item_id") val itemId: String, // 상품 id

    @Column(name = "user_id") val userId: Long, // user id
) {

    companion object {
        fun from(favoriteItem: FavoriteItem): FavoriteItemEntity {
            return FavoriteItemEntity(
                identifier = favoriteItem.id ?: UUID.randomUUID().toString(),
                itemId = favoriteItem.itemId,
                userId = favoriteItem.userId!!,
            )
        }
    }
}
