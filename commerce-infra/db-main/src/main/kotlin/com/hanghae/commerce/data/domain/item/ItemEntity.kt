package com.hanghae.commerce.data.domain.item

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Transient

@Entity
@Table(name = "item")
class ItemEntity protected constructor(
    @Transient
    private val id: String,

    @Column(nullable = false)
    private var name: String,

    @Column(nullable = false)
    private var price: Int,
) : PrimaryKeyEntity(id)
