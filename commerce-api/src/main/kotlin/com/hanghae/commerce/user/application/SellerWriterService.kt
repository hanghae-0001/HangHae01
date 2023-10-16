package com.hanghae.commerce.user.application

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserType
import com.hanghae.commerce.user.domain.UserWriter
import com.hanghae.commerce.user.presentation.dto.CreateSellerRequest
import com.hanghae.commerce.user.presentation.dto.CreateSellerResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class SellerWriterService(
    private val userWriter: UserWriter,
) {

    fun createSeller(request: CreateSellerRequest): CreateSellerResponse {
        val savedUser = userWriter.save(
            User(
                id = UUID.randomUUID().toString(),
                name = request.name,
                age = request.age,
                email = request.email,
                address = request.address,
                userType = UserType.SELLER,
            ),
        )

        return CreateSellerResponse(id = savedUser.id)
    }
}
