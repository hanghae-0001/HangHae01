package com.hanghae.commerce.data.domain.item

import com.hanghae.commerce.data.common.PrimaryKeyEntity
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
    var name: String,

    @Column(nullable = false)
    var price: Int,

    @Column(nullable = false)
    var stock: Long,

    var storeId: String,
) : PrimaryKeyEntity(identifier)
