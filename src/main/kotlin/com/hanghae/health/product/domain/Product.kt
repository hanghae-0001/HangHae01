package com.hanghae.health.product.domain

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
class Product(

    @Column(name = "name", nullable = false)
    @Comment("상품명")
    var name: String,

    @Column(name = "code", nullable = false, unique = true)
    @Comment("상품코드")
    var code: String,

    @Column(name = "description", nullable = false)
    @Comment("상품설명")
    var description: String,

    @Column(name = "brand", nullable = false)
    @Comment("브랜드")
     var brand: String,

    @Column(name = "maker", nullable = false)
    @Comment("제조사")
     var maker: String,

    @Column(name = "origin", nullable = false)
    @Comment("원산지")
     var origin: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    @Comment("카테고리")
     var category: Category,

    @Enumerated(EnumType.STRING)
    @Column(name = "temperature_zone", nullable = false)
    @Comment("온도대")
     var temperatureZone: TemperatureZone,

    @Column(name = "weight_in_grams", nullable = false)
    @Comment("무게(그램)")
     var weightInGrams: Long,

    @Embedded
     var productSize: ProductSize?,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    @Comment("상품 번호")
     var productNo: Long = 0L

}
