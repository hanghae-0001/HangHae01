package com.hanghae.commerce.data.domain.store

import com.hanghae.commerce.store.domain.Store
import com.hanghae.commerce.store.domain.StoreRepository
import org.springframework.stereotype.Repository

@Repository
class StoreEntityRepository(
    private val jpaStoreRepository: JpaStoreRepository,
) : StoreRepository {
    override fun save(store: Store): Store {
        return jpaStoreRepository.save(store.toEntity()).toDomain()
    }
}
