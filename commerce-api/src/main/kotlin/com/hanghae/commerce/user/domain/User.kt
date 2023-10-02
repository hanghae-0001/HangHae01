package com.hanghae.commerce.user.domain

class User(
    val name: String,
    val age: Int,
    val email: String,
    val address: String,
    val userType: UserType,
    var id: Long? = null,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
        if (email.isBlank()) {
            throw IllegalArgumentException("이메일은 비어 있을 수 없습니다")
        }
        if (address.isBlank()) {
            throw IllegalArgumentException("주소는 비어 있을 수 없습니다")
        }
    }

    companion object {
        fun createUser(name: String, age: Int, email: String, address: String, userType: UserType): User {
            return User(name, age, email, address, userType)
        }
    }
}
