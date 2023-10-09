package com.hanghae.commerce.store.domain

import org.springframework.stereotype.Component

@Component
class StoreWriter(
    private val storeRepository: StoreRepository,
) {
    fun save(store: Store): Store {
        return storeRepository.save(store)
    }

    fun allDelete() {
        return storeRepository.allDelete()
    }
}
