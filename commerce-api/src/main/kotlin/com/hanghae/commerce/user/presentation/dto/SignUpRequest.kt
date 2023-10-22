package com.hanghae.commerce.user.presentation.dto

import com.hanghae.commerce.user.domain.UserType

data class SignUpRequest (
    val account: String,
    var password: String,
    val name: String,
    val age: Int,
    val email: String,
    val address: String,
    val userType: UserType,
)
