package com.hanghae.commerce.user.presentation.dto

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserType

data class GetUserResponse(
    val id: String,
    val name: String,
    val age: Int,
    val email: String,
    val address: String,
    val userType: UserType,
) {
    companion object {
        fun of(user: User): GetUserResponse {
            return GetUserResponse(
                id = user.id,
                name = user.name,
                age = user.age,
                email = user.email,
                address = user.address,
                userType = user.userType,
            )
        }
    }
}
