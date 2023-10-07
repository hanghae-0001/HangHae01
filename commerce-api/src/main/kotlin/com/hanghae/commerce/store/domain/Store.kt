package com.hanghae.commerce.store.domain

class Store (
    val name: String,
    val userId: Long,
    var id: Long? = null,
){
    companion object {
        fun of(name: String, userId: Long): Store {
            return Store(name, userId)
        }
    }
}
