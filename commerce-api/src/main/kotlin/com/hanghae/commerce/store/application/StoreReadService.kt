package com.hanghae.commerce.store.application

import com.hanghae.commerce.store.domain.StoreReader
import com.hanghae.commerce.store.presentation.dto.GetStoreResponse
import com.hanghae.commerce.store.presentation.dto.GetStoresByUserIdResponse
import com.hanghae.commerce.user.domain.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreReadService(
    private val storeReader: StoreReader,
    private val userReader: UserReader,
) {
    @Transactional(readOnly = true)
    fun getStoresByUserId(userId: String): List<GetStoresByUserIdResponse> {
        val user = userReader.findById(userId) ?: throw IllegalArgumentException()
        val stores = storeReader.findStoresByUserId(user.id)
        return GetStoresByUserIdResponse.listOf(stores)
    }

    @Transactional(readOnly = true)
    fun getStore(storeId: String): GetStoreResponse {
        val store = storeReader.findStoreById(storeId) ?: throw IllegalArgumentException()
        return GetStoreResponse.of(store)
    }
}
