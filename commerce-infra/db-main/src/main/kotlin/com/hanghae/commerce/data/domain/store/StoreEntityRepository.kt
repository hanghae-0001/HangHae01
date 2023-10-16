package com.hanghae.commerce.data.domain.store

import com.hanghae.commerce.store.domain.Store
import com.hanghae.commerce.store.domain.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class StoreEntityRepository(
    private val jpaStoreRepository: JpaStoreRepository,
) : StoreRepository {
    override fun save(store: Store): Store {
        return jpaStoreRepository.save(store.toEntity()).toDomain()
    }

    override fun countSameStoreName(name: String): Int {
        return jpaStoreRepository.countSameStoreName(name)
    }

    override fun allDelete() {
        jpaStoreRepository.deleteAllInBatch()
    }

    override fun findStoreById(id: String): Store? {
        return jpaStoreRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findStoresByUserId(userId: String): List<Store> {
        return jpaStoreRepository.findStoresByUserId(userId).map { storeEntity -> storeEntity.toDomain() }
    }
}
