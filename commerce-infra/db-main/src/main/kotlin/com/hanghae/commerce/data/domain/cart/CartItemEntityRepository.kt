package com.hanghae.commerce.data.domain.cart

import com.hanghae.commerce.cart.domain.CartItem
import com.hanghae.commerce.cart.domain.CartItemRepository
import org.springframework.stereotype.Repository

@Repository
class CartItemEntityRepository(
    private val jpaCartItemRepository: JpaCartItemRepository,
) : CartItemRepository {
    override fun add(cartItem: CartItem): CartItem {
        var item = jpaCartItemRepository.findByCartIdAndItemId(cartItem.cartId, cartItem.itemId) ?: CartItemEntity.from(
            cartItem,
        )
        item = increaseQuantity(item)
        return CartItem(id = item.identifier, cartId = item.cartId, itemId = item.itemId, quantity = item.quantity)
    }

    private fun increaseQuantity(cartItemEntity: CartItemEntity): CartItemEntity {
        cartItemEntity.increaseQuantity()
        return jpaCartItemRepository.save(cartItemEntity)
    }

    override fun readByCartId(cartId: String): List<CartItem> {
        val findAllByCartId = jpaCartItemRepository.findAllByCartId(cartId)
        return findAllByCartId.map {
            CartItem(id = it.identifier, cartId = it.cartId, itemId = it.itemId, quantity = it.quantity)
        }
    }

    override fun readByCartIdAndItemId(cartId: String, itemId: String): CartItem? {
        return jpaCartItemRepository.findByCartIdAndItemId(cartId, itemId)?.let {
            CartItem(id = it.identifier, cartId = it.cartId, itemId = it.itemId, quantity = it.quantity)
        }
    }
}
