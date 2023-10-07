package com.hanghae.commerce.store.domain

import org.springframework.stereotype.Component

@Component
class StoreReader(
    private val storeRepository: StoreRepository
) {
}
