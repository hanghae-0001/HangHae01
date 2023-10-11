package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.presentaion.dto.CreateItemResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ItemReadService(
    private val itemReader: ItemReader,
) {

    @Transactional(readOnly = true)
    fun getItemsByStoreId(storeId: String): CreateItemResponse {

        val items = itemReader.getItemsByStoreId(storeId)

        return CreateItemResponse.of(savedItem)
    }
}
