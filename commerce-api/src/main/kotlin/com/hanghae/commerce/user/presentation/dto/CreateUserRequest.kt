package com.hanghae.commerce.user.presentation.dto

data class CreateUserRequest (
    val name: String,
    val age: Int,
    val email: String,
    val address: String,
)
