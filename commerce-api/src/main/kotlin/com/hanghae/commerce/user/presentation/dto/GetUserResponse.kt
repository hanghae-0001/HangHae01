package com.hanghae.commerce.user.presentation.dto

import com.hanghae.commerce.user.domain.User

data class GetUserResponse (
    val name: String,
    val age: Int,
    val email: String,
    val address: String,
    val id: Long,
) {
    companion object {
        fun of(user: User): GetUserResponse {
            return GetUserResponse(
                name = user.name,
                age = user.age,
                email = user.email,
                address = user.address,
                id = user.id!!,
            )
        }
    }
}
