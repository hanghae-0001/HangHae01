package com.hanghae.commerce.store.domain

import org.springframework.stereotype.Component
import java.util.*

@Component
class StoreReader(
    private val storeRepository: StoreRepository,
) {
    fun countSameStoreName(name: String): Int {
        return storeRepository.countSameStoreName(name)
    }

    fun findStoresByUserId(userId: String): List<Store> {
        return storeRepository.findStoresByUserId(userId)
    }

    fun findStoreById(id: String): Store? {
        return storeRepository.findStoreById(id)
    }
}
