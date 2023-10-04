package com.hanghae.commerce.user.application

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserReader
import com.hanghae.commerce.user.domain.UserWriter
import com.hanghae.commerce.user.presentation.dto.CreateUserRequest
import com.hanghae.commerce.user.presentation.dto.CreateUserResponse
import com.hanghae.commerce.user.presentation.dto.GetUserResponse
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userReader: UserReader,
    private val userWriter: UserWriter,
) {

    fun createUser(request: CreateUserRequest): CreateUserResponse {
        val savedUserId = userWriter.save(
            User.of(
                name = request.name,
                age = request.age,
                email = request.email,
                address = request.address,
                userType = request.userType,
            ),
        )

        return CreateUserResponse(id = savedUserId)
    }

    fun getUserById(userId: Long): GetUserResponse {
        val user = userReader.read(userId) ?: throw IllegalArgumentException()
        return GetUserResponse.of(user)
    }
}
