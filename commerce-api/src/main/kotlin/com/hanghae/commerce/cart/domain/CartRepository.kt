package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Repository

@Repository
interface CartRepository {
    fun readByUserId(userId: String): Cart?

    fun add(userId: String): Cart
}
