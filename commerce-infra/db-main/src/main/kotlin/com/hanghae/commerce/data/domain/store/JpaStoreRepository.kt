package com.hanghae.commerce.data.domain.store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaStoreRepository : JpaRepository<StoreEntity, String> {
    @Query("select count (s.id) from StoreEntity s where s.name = :name")
    fun countSameStoreName(name: String): Int

    @Query("select s from StoreEntity s where s.userId = :userId")
    fun findStoresByUserId(userId: String): List<StoreEntity>
}
