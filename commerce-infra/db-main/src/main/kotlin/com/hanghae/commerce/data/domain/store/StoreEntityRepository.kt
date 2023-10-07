package com.hanghae.commerce.data.domain.store

import org.springframework.stereotype.Repository

@Repository
class StoreEntityRepository (
    private val jpaStoreRepository: JpaStoreRepository
){

}
