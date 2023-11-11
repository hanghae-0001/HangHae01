package com.hanghae.commerce.data.domain.item

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaItemRepository : JpaRepository<ItemEntity, String> {
    fun findByIdIn(idList: List<String>): List<ItemEntity>
    fun findAllByStoreId(storeId: String): List<ItemEntity>

    @Query("select i from ItemEntity i where i.userId = :userId and i.storeId = :storeId")
    fun findAllItems(userId: String, storeId: String): List<ItemEntity>

    @Query("select i from ItemEntity i where i.userId = :userId and i.id = :itemId")
    fun findItem(userId: String, itemId: String): ItemEntity?
}
