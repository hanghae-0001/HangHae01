package com.hanghae.commerce.user.presentation.dto

import com.hanghae.commerce.user.domain.UserType

data class SignInResponse(
    val name: String,
    val type: UserType,
    val token: String,
)
