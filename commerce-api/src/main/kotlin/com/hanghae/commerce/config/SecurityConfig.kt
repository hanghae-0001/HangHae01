package com.hanghae.commerce.config

import com.hanghae.commerce.auth.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class SecurityConfig(private val jwtAuthenticationFilter: JwtAuthenticationFilter) {
    private val allowedUrls = arrayOf("/", "/swagger-ui/**", "/v3/**", "/sign-up", "/sign-in")	// sign-up, sign-in 추가

    @Bean
    fun filterChain(http: HttpSecurity) = http
        .csrf().disable()
        .headers { it.frameOptions().sameOrigin() }
        .authorizeHttpRequests {
            it.requestMatchers(*allowedUrls).permitAll()
                .anyRequest().authenticated()
        }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)	// 추가
        .build()!!

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
