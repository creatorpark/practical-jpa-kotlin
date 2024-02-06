package sample.jpa.a_entiity.id

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.util.*


// 3rd-Party는 Key가 String 일 수 있음.
@MappedSuperclass
class StringPk(
    id: String
) : Persistable<String> {

    @Id
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("_id")
    val id: String = id

    override fun getId(): String = id

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
            (obj as StringPk).id
        }
    }

    override fun hashCode() = Objects.hashCode(id)
}
