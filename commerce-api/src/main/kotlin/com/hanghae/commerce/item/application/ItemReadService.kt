package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.presentaion.dto.GetItemsByStoreIdResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemReadService(
    private val itemReader: ItemReader,
) {

    @Transactional(readOnly = true)
    fun getItemsByStoreId(storeId: String): List<GetItemsByStoreIdResponse> {

        val items = itemReader.getItemsByStoreId(storeId)

        return GetItemsByStoreIdResponse.listOf(items)
    }
}
