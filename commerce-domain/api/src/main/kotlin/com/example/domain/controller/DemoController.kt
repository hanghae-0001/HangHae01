package com.example.domain.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/demo")
@RestController
class DemoController() {

    @GetMapping
    fun getHealth(): String {
        return "ok"
    }
}
