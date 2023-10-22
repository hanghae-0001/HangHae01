package com.hanghae.commerce.user.presentation

import com.hanghae.commerce.user.application.SignService
import com.hanghae.commerce.user.presentation.dto.SignInRequest
import com.hanghae.commerce.user.presentation.dto.SignUpRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class SignController(private val signService: SignService) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest) = signService.registerUser(request)

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest) = signService.signIn(request)
}
