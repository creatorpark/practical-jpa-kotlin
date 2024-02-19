package sample.jpa.a_entiity.id

import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable

//@MappedSuperclass
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
        println("other is HibernateProxy " + (other is HibernateProxy))
        val otherClass = if (other is HibernateProxy) {
            other.hibernateLazyInitializer.persistentClass
        } else {
            other::class.java
        }
        println("this is HibernateProxy " + (this is HibernateProxy))
        val thisClass = if (this is HibernateProxy) {
            this.hibernateLazyInitializer.persistentClass
        } else {
            this::class.java
        }

        if (thisClass != otherClass) return false
        return equalsId(other)
    }

    override fun hashCode(): Int {
        return if (this is HibernateProxy) {
            this.hibernateLazyInitializer.persistentClass.hashCode()
        } else {
            javaClass.hashCode()
        }
    }

    protected abstract fun equalsId(other: Any): Boolean
}

