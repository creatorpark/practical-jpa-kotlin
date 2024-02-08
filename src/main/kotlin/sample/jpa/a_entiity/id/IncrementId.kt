package sample.jpa.a_entiity.id

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.proxy.HibernateProxy

@MappedSuperclass
class IncrementId : AbstractId<Long>() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("_id")
    val id: Long = 0

    override fun getId(): Long = id

    override fun idCompare(obj: Any): Boolean {
        return if (obj is HibernateProxy) {
            id != 0L && id == obj.hibernateLazyInitializer.identifier
        } else {
            id != 0L && id == (obj as IncrementId).id
        }
    }
}
