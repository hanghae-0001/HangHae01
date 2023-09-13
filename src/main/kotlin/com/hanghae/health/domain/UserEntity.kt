package com.hanghae.health.domain

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter


@Getter
@Setter
@Entity
@Table(name = "users")
data class UserEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column
    val name: String,
) {
}
