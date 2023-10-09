package com.hanghae.commerce.store.domain

interface StoreRepository {
    fun save(store: Store): Store
}
