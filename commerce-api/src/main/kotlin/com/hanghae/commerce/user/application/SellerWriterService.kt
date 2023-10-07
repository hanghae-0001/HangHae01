package com.hanghae.commerce.user.application

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserType
import com.hanghae.commerce.user.domain.UserWriter
import com.hanghae.commerce.user.presentation.dto.*
import org.springframework.stereotype.Service

@Service
class SellerWriterService(
    private val userWriter: UserWriter,
) {

    fun createSeller(request: CreateSellerRequest): CreateSellerResponse {
        val savedUserId = userWriter.save(
            User.of(
                name = request.name,
                age = request.age,
                email = request.email,
                address = request.address,
                userType = UserType.SELLER,
            ),
        )

        return CreateSellerResponse(id = savedUserId)
    }
}
