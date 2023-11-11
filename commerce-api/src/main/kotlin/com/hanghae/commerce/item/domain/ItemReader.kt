package com.hanghae.commerce.item.domain

import org.springframework.stereotype.Component

@Component
class ItemReader(
    private val itemRepository: ItemRepository,
) {
    fun read(idList: List<String>): List<Item> {
        return itemRepository.findByIdIn(idList)
    }

    fun getItemsByStoreId(storeId: String): List<Item> {
        return itemRepository.findAllByStoreId(storeId)
    }

    fun getItemByItemId(itemId: String): Item? {
        return itemRepository.findById(itemId)
    }

    fun getItems(userId: String, storeId: String): List<Item> {
        return itemRepository.findAllItems(userId, storeId)
    }

    fun getItem(userId: String, itemId: String): Item? {
        return itemRepository.findItem(userId, itemId)
    }
}
