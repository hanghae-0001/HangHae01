package com.hanghae.commerce.interceptor

import com.hanghae.commerce.common.extension.log
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.time.Instant

class HttpRequestLoggingInterceptor : HandlerInterceptor {

    private val logger = KotlinLogging.logger { }

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        logger.info { "${request.log()} : start" }
        request.setAttribute("startTime", Instant.now())
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {
        val startTime = request.getAttribute("startTime") as Instant
        val endTime = Instant.now()

        val processingTimeMillis = endTime.toEpochMilli() - startTime.toEpochMilli()

        when {
            processingTimeMillis > 5000 -> logger.error { "${request.log()} Significant Slow API: ${processingTimeMillis}ms" }
            processingTimeMillis > 3000 -> logger.warn { "${request.log()} Slow API: ${processingTimeMillis}ms" }
        }
    }
}
