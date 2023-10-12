package com.hanghae.commerce.data.domain.store

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import com.hanghae.commerce.store.domain.Store
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "store")
data class StoreEntity(
    @Transient
    private val identifier: String,
    private val name: String,
    private val userId: String,
) : PrimaryKeyEntity(identifier) {
    fun toDomain(): Store {
        return Store(
            id = this.id,
            name = this.name,
            userId = this.userId,
        )
    }
}
