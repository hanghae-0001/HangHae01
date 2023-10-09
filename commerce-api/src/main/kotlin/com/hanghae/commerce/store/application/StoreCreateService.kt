package com.hanghae.commerce.store.application

import com.hanghae.commerce.store.domain.Store
import com.hanghae.commerce.store.domain.StoreWriter
import com.hanghae.commerce.store.presentation.dto.CreateStoreRequest
import com.hanghae.commerce.store.presentation.dto.CreateStoreResponse
import com.hanghae.commerce.user.domain.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StoreCreateService(
    private val storeWriter: StoreWriter,
    private val userReader: UserReader,
) {

    @Transactional
    fun createStore(request: CreateStoreRequest):  CreateStoreResponse{
        val user = userReader.findById(request.userId) ?: throw IllegalArgumentException()
        val store = Store.of(id = UUID.randomUUID().toString(), name = request.name, userId = user.id)
        val savedStore = storeWriter.save(store)
        return CreateStoreResponse.of(savedStore)
    }
}
