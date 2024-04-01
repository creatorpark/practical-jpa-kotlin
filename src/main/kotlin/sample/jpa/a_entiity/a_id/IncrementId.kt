package sample.jpa.a_entiity.a_id

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.proxy.HibernateProxy

/**
 * 저장되기 전에는 null일 수 있지만, 그 이후에는 항상 값을 가지므로 getId는 단언문을 사용하였다.
 * 저장되기 전에 ID가 사용되는 경우는 Entity로 Set을 만드는 경우다.
 */
@MappedSuperclass
class IncrementId : AbstractId<Long>() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("_id")
    val id: Long? = null

    override fun getId(): Long = id!!

    override fun equalsId(obj: Any): Boolean {
        return if (obj is HibernateProxy) {
            id != null && id == obj.hibernateLazyInitializer.identifier
        } else {
            id != null && id == (obj as IncrementId).id
        }
    }
}
