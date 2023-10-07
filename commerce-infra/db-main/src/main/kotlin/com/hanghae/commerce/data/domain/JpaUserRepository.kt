package com.hanghae.commerce.data.domain

import org.springframework.data.jpa.repository.JpaRepository

internal interface JpaUserRepository : JpaRepository<UserEntity, Long>
