package com.hanghae.health.domain.order

import com.hanghae.health.domain.product.Product
import com.hanghae.health.domain.user.User
import jakarta.persistence.*

@Entity
class Order (
    @ManyToOne
    val user: User?,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val products: MutableList<Product> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
){


}
