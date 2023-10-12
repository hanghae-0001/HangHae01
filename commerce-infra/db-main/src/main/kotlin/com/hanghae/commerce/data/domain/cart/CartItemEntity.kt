package com.hanghae.commerce.data.domain.cart

import com.hanghae.commerce.cart.domain.CartItem
import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "cart_item",
    uniqueConstraints = [UniqueConstraint(name = "UniqueCartAndItem", columnNames = ["cart_id", "item_id"])],
)
class CartItemEntity(
    @Id val identifier: String,

    @Column(name = "item_id") val itemId: String, // 상품 id

    @Column var quantity: Int, // 수량

    @Column(name = "cart_id") val cartId: String, // 장바구니 id
) {
    companion object {
        fun from(cartItem: CartItem): CartItemEntity {
            return CartItemEntity(
                identifier = cartItem.id ?: UUID.randomUUID().toString(),
                itemId = cartItem.itemId,
                quantity = cartItem.quantity,
                cartId = cartItem.cartId,
            )
        }
    }

    fun increaseQuantity() {
        this.quantity += 1
    }
}
