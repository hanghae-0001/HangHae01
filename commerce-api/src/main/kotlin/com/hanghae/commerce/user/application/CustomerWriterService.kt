package com.hanghae.commerce.user.application

import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserType
import com.hanghae.commerce.user.domain.UserWriter
import com.hanghae.commerce.user.presentation.dto.CreateCustomerRequest
import com.hanghae.commerce.user.presentation.dto.CreateCustomerResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerWriterService(
    private val userWriter: UserWriter,
) {

    fun createCustomer(request: CreateCustomerRequest): CreateCustomerResponse {
        val savedUser = userWriter.save(
            User(
                id = UUID.randomUUID().toString(),
                name = request.name,
                age = request.age,
                email = request.email,
                address = request.address,
                userType = UserType.CUSTOMER,
            ),
        )

        return CreateCustomerResponse(id = savedUser.id)
    }
}
