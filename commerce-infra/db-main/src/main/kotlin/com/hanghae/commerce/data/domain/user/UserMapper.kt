package com.hanghae.commerce.data.domain.user

import com.hanghae.commerce.user.domain.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        identifier = this.id,
        name = this.name,
        age = this.age,
        email = this.email,
        address = this.address,
        userType = this.userType,
    )
}
