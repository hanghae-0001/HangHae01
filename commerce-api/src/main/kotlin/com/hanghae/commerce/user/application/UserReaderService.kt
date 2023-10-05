package com.hanghae.commerce.user.application

import com.hanghae.commerce.user.domain.UserReader
import com.hanghae.commerce.user.presentation.dto.GetUserResponse
import org.springframework.stereotype.Service

@Service
class UserReaderService(
    private val userReader: UserReader,
) {

    fun getUserById(userId: Long): GetUserResponse {
        val user = userReader.read(userId) ?: throw IllegalArgumentException()
        return GetUserResponse.of(user)
    }
}
