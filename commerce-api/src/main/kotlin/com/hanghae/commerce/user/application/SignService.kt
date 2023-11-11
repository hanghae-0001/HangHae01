package com.hanghae.commerce.user.application

import com.hanghae.commerce.auth.TokenProvider
import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserReader
import com.hanghae.commerce.user.domain.UserWriter
import com.hanghae.commerce.user.presentation.dto.SignInRequest
import com.hanghae.commerce.user.presentation.dto.SignInResponse
import com.hanghae.commerce.user.presentation.dto.SignUpRequest
import com.hanghae.commerce.user.presentation.dto.SignUpResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignService(
    private val userReader: UserReader,
    private val userWriter: UserWriter,
    private val tokenProvider: TokenProvider,
    private val encoder: PasswordEncoder,
) {
    @Transactional
    fun registerUser(request: SignUpRequest): SignUpResponse {
        val countUserByAccount = userReader.countUserByAccount(request.account)

        if (countUserByAccount > 0) {
            throw IllegalArgumentException("이미 사용중인 아이디입니다.")
        }

        val user = userWriter.save(User.from(request, encoder))

        return SignUpResponse.from(user)
    }

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        val user = userReader.findByAccount(request.account)
            ?.takeIf { encoder.matches(request.password, it.password) }	// 암호화된 비밀번호와 비교하도록 수정
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        val token = tokenProvider.createToken("${user.id}:${user.userType}")
        return SignInResponse(user.name, user.userType, token)
    }
}
