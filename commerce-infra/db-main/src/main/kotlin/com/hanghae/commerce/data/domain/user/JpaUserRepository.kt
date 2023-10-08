package com.hanghae.commerce.data.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : JpaRepository<UserEntity, String>
