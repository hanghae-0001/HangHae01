package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemRepository
import org.springframework.stereotype.Component

@Component
class ItemReader(
    private val itemRepository: ItemRepository,
) {
    fun read(idList: List<String>): List<Item> {
        return itemRepository.findByIdIn(idList)
    }
}
