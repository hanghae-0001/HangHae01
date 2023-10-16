package com.hanghae.commerce.data.domain.order

import org.springframework.data.jpa.repository.JpaRepository

interface JpaOrderRepository : JpaRepository<OrderEntity, String>
