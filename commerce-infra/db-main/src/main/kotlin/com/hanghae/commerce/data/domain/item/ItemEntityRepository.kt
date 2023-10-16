package com.hanghae.commerce.data.domain.item

import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ItemEntityRepository(
    private val jpaItemRepository: JpaItemRepository,
) : ItemRepository {

    override fun save(item: Item): Item {
        return jpaItemRepository.save(item.toEntity()).toDomain()
    }

    override fun findById(id: String): Item? {
        return jpaItemRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findAll(): List<Item> {
        return jpaItemRepository.findAll().map { it.toDomain() }
    }

    override fun findByIdIn(idList: List<String>): List<Item> {
        return jpaItemRepository.findByIdIn(idList).map { it.toDomain() }
    }

    override fun deleteById(id: String) {
        jpaItemRepository.deleteById(id)
    }

    override fun deleteAll() {
        jpaItemRepository.deleteAll()
    }

    override fun findAllByStoreId(storeId: String): List<Item> {
        return jpaItemRepository.findAllByStoreId(storeId).map { it.toDomain() }
    }
}
