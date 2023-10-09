package com.hanghae.commerce.event

import java.time.LocalDateTime

interface Event {
    fun time(): LocalDateTime {
        return LocalDateTime.now()
    }
}
