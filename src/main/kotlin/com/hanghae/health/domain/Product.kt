package com.hanghae.health.domain

import jakarta.persistence.*
import lombok.Getter

@Getter
@Entity
@Table(name ="product")
class Product (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long? = null,

    @Column
    val name: String, // 상품 이름

    @Column
    val category: String, // 카테고리

    @Column
    val price: Int, // 가격

    @Column
    val count: Int // 수량
)

