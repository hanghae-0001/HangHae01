package com.hanghae.health.repository

import com.hanghae.health.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserEntityRepository : JpaRepository<UserEntity, Long> {
}
