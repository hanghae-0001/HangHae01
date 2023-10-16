package com.hanghae.commerce.data.domain.cart

import org.springframework.data.jpa.repository.JpaRepository

interface JpaCartItemRepository : JpaRepository<CartItemEntity, String> {
    fun findAllByCartId(cartId: String): List<CartItemEntity>
    fun findByCartIdAndItemId(cartId: String, itemId: String): CartItemEntity?
}
