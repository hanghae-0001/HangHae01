package com.hanghae.health.common

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Table
import jakarta.persistence.metamodel.EntityType
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Component
abstract class DatabaseCleaner: InitializingBean {

    @PersistenceContext
    private val entityManager: EntityManager? = null

    private var tableNames: Set<String>? = null


    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        tableNames = entityManager!!.metamodel.entities.stream()
            .filter { e: EntityType<*> ->
                e.javaType.isAnnotationPresent(
                    Table::class.java,
                )
            }
            .map{ e ->
                e.javaType.getAnnotation(
                    Table::class.java
                ).name
            }
            .collect(Collectors.toUnmodifiableSet())

    }

    @Transactional
    fun execute() {
        entityManager!!.flush()
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate()
        for (tableName: String in tableNames!!) {
            entityManager.createNativeQuery("TRUNCATE TABLE $tableName RESTART IDENTITY ").executeUpdate()
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate()
    }
}
