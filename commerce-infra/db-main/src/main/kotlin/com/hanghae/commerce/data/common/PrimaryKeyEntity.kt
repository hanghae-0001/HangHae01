package com.hanghae.commerce.data.common

import com.github.f4b6a3.ulid.UlidCreator
import com.hanghae.commerce.common.IdentifierConstants
import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.util.*
import kotlin.jvm.Transient

@MappedSuperclass
abstract class PrimaryKeyEntity(id: String) : Persistable<String> {
    @Id
    @Column(columnDefinition = "varchar(36)")
    private val id: String =
        if (id == IdentifierConstants.NOT_YET_PERSISTED_ID) UlidCreator.getMonotonicUlid().toUuid().toString() else id

    @Transient
    private var _isNew = if (id == IdentifierConstants.NOT_YET_PERSISTED_ID) true else false

    override fun getId(): String = id

    override fun isNew(): Boolean = _isNew

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Serializable {
        return if (obj is HibernateProxy) {
            ({ obj.hibernateLazyInitializer.identifier }) as Serializable
        } else {
            (obj as PrimaryKeyEntity).id
        }
    }

    override fun hashCode() = Objects.hashCode(id)

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }
}
