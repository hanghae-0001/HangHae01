package com.hanghae.commerce.item.domain

interface ItemRepository {

    fun save(item: Item): Item

    fun findById(id: String): Item?

    fun findAll(): List<Item>

    fun findByIdIn(idList: List<String>): List<Item>

    fun deleteById(id: String)

    fun deleteAll(): Unit

    fun findAllByStoreId(storeId: String): List<Item>
}
