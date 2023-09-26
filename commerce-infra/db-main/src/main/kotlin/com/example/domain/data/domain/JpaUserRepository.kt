package com.example.domain.data.domain

import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : JpaRepository<UserEntity, Long>
