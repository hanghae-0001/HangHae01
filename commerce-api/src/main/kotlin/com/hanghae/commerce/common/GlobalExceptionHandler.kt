package com.hanghae.commerce.common

import com.hanghae.commerce.common.extension.log
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    val logger = KotlinLogging.logger { }

    @ExceptionHandler(Exception::class)
    fun custom(
        request: HttpServletRequest,
        exception: Exception,
    ): ResponseEntity<String> {
        logger.error(exception) { "${request.log()} - raise exception" }
        return ResponseEntity.internalServerError().body(exception.javaClass.name)
    }
}
