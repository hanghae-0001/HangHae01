package com.hanghae.health.product.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.function.Supplier

interface ProductJpaRepository: JpaRepository<Product, Long> {

//    fun getBy(productNo: Long?): Product? {
//        return productNo?.let {
//            findById(it)
//                .orElseThrow<IllegalArgumentException>(
//                    Supplier<IllegalArgumentException> {
//                        IllegalArgumentException(
//                            "상품이 존재하지 않습니다. %d".formatted(productNo),
//                        )
//                    },
//                )
//        }
//    }

}
