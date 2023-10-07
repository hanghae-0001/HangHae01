package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemRepository
import org.springframework.stereotype.Component

@Component
class ItemWriter(
    private val itemRepository: ItemRepository,
) {
    fun write(item: Item) {
        itemRepository.save(item)
    }

    fun deleteAll() {
        itemRepository.deleteAll()
    }
}
