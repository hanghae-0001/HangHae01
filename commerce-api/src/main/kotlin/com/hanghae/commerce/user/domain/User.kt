package com.hanghae.commerce.user.domain

import com.hanghae.commerce.common.IdentifierConstants
import com.hanghae.commerce.user.presentation.dto.SignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder

class User(
    val id: String = IdentifierConstants.NOT_YET_PERSISTED_ID,
    val account: String,
    var password: String,
    var name: String,
    var age: Int,
    val email: String,
    val address: String,
    val userType: UserType,
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
        fun from(request: SignUpRequest, encoder: PasswordEncoder) =  User (
            account = request.account,
            password = encoder.encode(request.password),
            name = request.name,
            age = request.age,
            email = request.email,
            address = request.address,
            userType = request.userType,
        )
    }
}
