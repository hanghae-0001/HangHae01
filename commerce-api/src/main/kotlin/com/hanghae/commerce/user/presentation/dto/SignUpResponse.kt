package com.hanghae.commerce.user.presentation.dto

import com.hanghae.commerce.user.domain.User

data class SignUpResponse(
    val id: String,
    val account: String,
    val name: String,
    val age: Int,
    val email: String,
){
    companion object {
        fun from(user: User) = SignUpResponse(
            id = user.id,
            account = user.account,
            name = user.name,
            age = user.age,
            email = user.email,
        )
    }
}
