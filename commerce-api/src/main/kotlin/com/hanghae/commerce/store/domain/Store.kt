package com.hanghae.commerce.store.domain

class Store(
    val id: String,
    val name: String,
    val userId: String,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }
    companion object {
        fun of(id: String, name: String, userId: String): Store {
            return Store(id, name, userId)
        }
    }
}
