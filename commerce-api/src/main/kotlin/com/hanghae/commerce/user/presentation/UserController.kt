package com.hanghae.commerce.user.presentation

import com.hanghae.commerce.user.application.UserService
import com.hanghae.commerce.user.presentation.dto.CreateUserRequest
import com.hanghae.commerce.user.presentation.dto.CreateUserResponse
import com.hanghae.commerce.user.presentation.dto.GetUserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {

    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse {
        return userService.createUser(createUserRequest)
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Long): GetUserResponse {
        return userService.getUserById(userId)
    }
}
