package com.hanghae.health.product.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class ProductSize(
    @Column(name = "width_in_millimeters", nullable = false)
    @Comment("상품 너비(mm)")
    private var widthInMillimeters: Long = 0,

    @Column(name = "height_in_millimeters", nullable = false)
    @Comment("상품 높이(mm)")
    private var heightInMillimeters: Long = 0,

    @Column(name = "length_in_millimeters", nullable = false)
    @Comment("상품 길이(mm)")
    private var lengthInMillimeters: Long = 0,
) {

}
