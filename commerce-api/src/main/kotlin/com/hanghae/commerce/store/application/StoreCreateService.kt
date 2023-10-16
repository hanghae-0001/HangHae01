package com.hanghae.commerce.store.application

import com.hanghae.commerce.store.domain.Store
import com.hanghae.commerce.store.domain.StoreReader
import com.hanghae.commerce.store.domain.StoreWriter
import com.hanghae.commerce.store.presentation.dto.CreateStoreRequest
import com.hanghae.commerce.store.presentation.dto.CreateStoreResponse
import com.hanghae.commerce.user.domain.UserReader
import com.hanghae.commerce.user.domain.UserType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StoreCreateService(
    private val storeWriter: StoreWriter,
    private val storeReader: StoreReader,
    private val userReader: UserReader,
) {

    @Transactional
    fun createStore(request: CreateStoreRequest): CreateStoreResponse {
        val user = userReader.findById(request.userId) ?: throw IllegalArgumentException()

        if (!user.userType.equals(UserType.SELLER)) {
            throw IllegalArgumentException("해당 유저는 판매자가 아닙니다.")
        }

        if (storeReader.countSameStoreName(request.name) > 0) {
            throw IllegalArgumentException("상점이름이 중복됩니다.")
        }

        val store = Store.of(id = UUID.randomUUID().toString(), name = request.name, userId = user.id)

        val savedStore = storeWriter.save(store)
        return CreateStoreResponse.of(savedStore)
    }
}
