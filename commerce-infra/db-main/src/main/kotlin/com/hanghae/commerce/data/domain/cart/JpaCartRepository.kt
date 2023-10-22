package com.hanghae.commerce.data.domain.cart

import org.springframework.data.jpa.repository.JpaRepository

interface JpaCartRepository : JpaRepository<CartEntity, String> {
    fun findByUserId(userId: String): CartEntity?
}
