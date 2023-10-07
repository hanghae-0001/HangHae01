package com.hanghae.commerce.user.presentation

import com.hanghae.commerce.user.application.CustomerWriterService
import com.hanghae.commerce.user.presentation.dto.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController(
    private val customerWriterService: CustomerWriterService,
) {

    @PostMapping
    fun createCustomer(@RequestBody createCustomerRequest: CreateCustomerRequest): CreateCustomerResponse {
        return customerWriterService.createCustomer(createCustomerRequest)
    }
}
