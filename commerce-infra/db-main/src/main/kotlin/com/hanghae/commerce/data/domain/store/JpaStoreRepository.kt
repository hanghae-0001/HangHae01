package com.hanghae.commerce.data.domain.store

import org.springframework.data.jpa.repository.JpaRepository

interface JpaStoreRepository : JpaRepository<StoreEntity, Long>
