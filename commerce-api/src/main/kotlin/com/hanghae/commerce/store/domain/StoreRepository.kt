package com.hanghae.commerce.store.domain

interface StoreRepository {
    fun save(store: Store): Store
    fun countSameStoreName(name: String): Int
    fun allDelete()
    fun findStoreById(id: String): Store?
    fun findStoresByUserId(userId: String): List<Store>
}
