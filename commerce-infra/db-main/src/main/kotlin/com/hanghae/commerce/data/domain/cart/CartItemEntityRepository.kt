package com.hanghae.commerce.data.domain.cart

import com.hanghae.commerce.cart.domain.CartItem
import com.hanghae.commerce.cart.domain.CartItemRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CartItemEntityRepository(
    private val jpaCartItemRepository: JpaCartItemRepository,
) : CartItemRepository {
    @Transactional
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

    @Transactional(readOnly = true)
    override fun readByCartId(cartId: String): List<CartItem> {
        val findAllByCartId = jpaCartItemRepository.findAllByCartId(cartId)
        return findAllByCartId.map {
            CartItem(id = it.identifier, cartId = it.cartId, itemId = it.itemId, quantity = it.quantity)
        }
    }

    @Transactional(readOnly = true)
    override fun readByCartIdAndItemId(cartId: String, itemId: String): CartItem? {
        return jpaCartItemRepository.findByCartIdAndItemId(cartId, itemId)?.let {
            CartItem(id = it.identifier, cartId = it.cartId, itemId = it.itemId, quantity = it.quantity)
        }
    }

    @Transactional
    override fun save(cartItem: CartItem): CartItem {
//        var entity = jpaCartItemRepository.findByCartIdAndItemId(cartItem.cartId, cartItem.itemId) ?: CartItemEntity.from(
//            cartItem,
//        )
        val entity = jpaCartItemRepository.save(CartItemEntity.from(cartItem))
        return CartItem(
            id = entity.identifier,
            cartId = entity.cartId,
            itemId = entity.itemId,
            quantity = entity.quantity,
        )
    }
}
