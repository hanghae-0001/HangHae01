package com.hanghae.commerce.data.domain.item

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import com.hanghae.commerce.item.domain.Item
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Transient

@Entity
@Table(name = "item")
class ItemEntity(
    @Transient
    private val identifier: String,

    @Column(nullable = false)
    private var name: String,

    @Column(nullable = false)
    private var price: Int,

    @Column(nullable = false)
    private var stock: Long,

    private var storeId: String,
) : PrimaryKeyEntity(identifier){
    fun toDomain(): Item {
        return Item(
            id = this.id,
            name = this.name,
            price = this.price,
            stock = this.stock,
            storeId = this.storeId,
        )
    }
}
