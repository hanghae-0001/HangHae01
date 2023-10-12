package com.hanghae.commerce.data.domain.item

import org.springframework.data.jpa.repository.JpaRepository

interface JpaItemRepository : JpaRepository<ItemEntity, String> {
    fun findByIdIn(idList: List<String>): List<ItemEntity>
    fun findAllByStoreId(storeId: String): List<ItemEntity>
}
