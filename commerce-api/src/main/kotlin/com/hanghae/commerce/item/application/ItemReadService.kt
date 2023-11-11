package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.domain.ItemReader
import com.hanghae.commerce.item.presentaion.dto.GetItemByItemIdResponse
import com.hanghae.commerce.item.presentaion.dto.GetItemsByStoreIdResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemReadService(
    private val itemReader: ItemReader,
) {

    @Transactional(readOnly = true)
    fun getItems(userId: String, storeId: String): List<GetItemsByStoreIdResponse> {
        val items = itemReader.getItems(userId, storeId)

        return GetItemsByStoreIdResponse.listOf(items)
    }

    @Transactional(readOnly = true)
    fun getItem(userId: String, itemId: String): GetItemByItemIdResponse {
        val item = itemReader.getItem(userId, itemId) ?: throw IllegalArgumentException()

        return GetItemByItemIdResponse.of(item)
    }
}
