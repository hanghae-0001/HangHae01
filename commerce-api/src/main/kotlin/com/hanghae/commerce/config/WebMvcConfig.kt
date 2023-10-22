package com.hanghae.commerce.config

import com.hanghae.commerce.interceptor.HttpRequestLoggingInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(HttpRequestLoggingInterceptor())
            .excludePathPatterns(
                "/favicon.ico",
                "/error",
                "/actuator/**",
            )
    }
}
