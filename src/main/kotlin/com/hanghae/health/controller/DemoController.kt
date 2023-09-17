package com.hanghae.health.controller

import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RequestMapping("/demo")
@RestController
class DemoController(){

    @GetMapping
    fun getHealth(): String {
        return "ok"
    }
}