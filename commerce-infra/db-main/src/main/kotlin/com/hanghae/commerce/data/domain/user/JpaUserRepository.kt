package com.hanghae.commerce.data.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaUserRepository : JpaRepository<UserEntity, String> {
    @Query("select count(u) from UserEntity u where u.account = :account")
    fun countUserByAccount(account: String): Integer

    @Query("select u from UserEntity u where u.account = :account")
    fun findByAccount(account: String): UserEntity?
}
