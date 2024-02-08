package sample.jpa.a_entiity.id

import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.util.*

@MappedSuperclass
abstract class AbstractId<ID> : Persistable<ID> {
    abstract override fun getId(): ID

    @Transient
    private var _isNew = true
    override fun isNew(): Boolean = _isNew

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false

        val otherClass = if (other is HibernateProxy) {
            other.hibernateLazyInitializer.persistentClass
        } else {
            other::class.java
        }

        val thisClass = if (this is HibernateProxy) {
            this.hibernateLazyInitializer.persistentClass
        } else {
            this::class.java
        }

        if (thisClass != otherClass) return false
        return idCompare(other)
    }

    override fun hashCode(): Int {
        return if (this is HibernateProxy) {
            this.hibernateLazyInitializer.persistentClass.hashCode()
        } else {
            Objects.hashCode(getId())
        }
    }


    protected abstract fun idCompare(other: Any): Boolean
}

