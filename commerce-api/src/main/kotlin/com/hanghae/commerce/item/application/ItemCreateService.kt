package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemWriter
import com.hanghae.commerce.item.presentaion.dto.CreateItemRequest
import com.hanghae.commerce.item.presentaion.dto.CreateItemResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ItemCreateService(
    private val itemWriter: ItemWriter,
) {

    @Transactional
    fun createItem(request: CreateItemRequest): CreateItemResponse {
        val item = Item.of(
            id = UUID.randomUUID().toString(),
            name = request.name,
            price = request.price,
            stock = request.stock,
            storeId = request.storeId,
        )

        val savedItem = itemWriter.save(item)

        return CreateItemResponse.of(savedItem)
    }
}
