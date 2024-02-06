package sample.jpa.a_entiity.id

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.util.*
import kotlin.jvm.Transient

@MappedSuperclass
class LongPk : Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("_id")
    val id: Long? = null

    override fun getId(): Long? = id

    @Transient
    private var _isNew = true
    override fun isNew(): Boolean = _isNew

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }
        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Any? {
        return if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier
        } else {
            (obj as LongPk).id
        }
    }

    override fun hashCode() = Objects.hashCode(id)
}
