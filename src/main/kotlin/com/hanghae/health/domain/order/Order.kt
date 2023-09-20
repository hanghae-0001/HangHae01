package com.hanghae.health.domain.order

import com.hanghae.health.domain.product.Product
import com.hanghae.health.domain.user.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Order(
    @ManyToOne
    val user: User?,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val products: MutableList<Product> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    fun addProduct(product: Product) {
        products.add(product)
    }
}
