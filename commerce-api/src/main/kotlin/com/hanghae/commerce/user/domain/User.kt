package com.hanghae.commerce.user.domain

class User(
    val name: String,
    val age: Int,
    val email: String,
    val address: String,
    var id: Long? = null,
){
    companion object {
        fun createUser(name: String, age: Int, email: String, address: String): User {
            return User(name, age, email, address)
        }
    }
}
