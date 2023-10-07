package com.hanghae.commerce.store.domain

import org.springframework.stereotype.Component

@Component
class StoreWriter(
    private val storeRepository: StoreRepository
){
}
