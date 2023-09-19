package com.hanghae.health.repository

import com.hanghae.health.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductEntityRepository : JpaRepository<Product, Long> {
}
