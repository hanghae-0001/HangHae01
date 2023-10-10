package com.hanghae.commerce.data.domain.cart

import com.hanghae.commerce.cart.domain.Cart
import com.hanghae.commerce.cart.domain.CartRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CartEntityRepository(
    private val jpaCartRepository: JpaCartRepository,
) : CartRepository {

    @Transactional(readOnly = true)
    override fun readByUserId(userId: Long): Cart? {
        return jpaCartRepository.findByUserId(userId)?.let {
            Cart(
                id = it.identifier,
                userId = it.userId,
            )
        }
    }

    @Transactional
    override fun add(userId: Long): Cart? {
        val cartEntity = jpaCartRepository.save(CartEntity.from(userId))
        return Cart(id = cartEntity.identifier, userId = cartEntity.userId)
    }
}
