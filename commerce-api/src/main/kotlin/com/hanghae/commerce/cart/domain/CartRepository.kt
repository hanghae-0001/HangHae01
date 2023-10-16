package com.hanghae.commerce.cart.domain

import org.springframework.stereotype.Repository

@Repository
interface CartRepository {
    fun readByUserId(userId: Long): Cart?

    fun add(userId: Long): Cart?
}
