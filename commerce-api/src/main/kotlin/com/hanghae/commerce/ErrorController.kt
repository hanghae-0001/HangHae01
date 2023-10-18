package com.hanghae.commerce

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/raise")
class ErrorController {

    @GetMapping("/exception")
    fun exception(): ResponseEntity<String> {
        throw IllegalArgumentException("api called - exception")
    }

    @GetMapping("/timeout/{seconds}")
    fun timeout(
        @PathVariable seconds: Long,
    ): ResponseEntity<String> {
        Thread.sleep(1000 * seconds)
        return ResponseEntity.ok("api called - timeout")
    }
}
