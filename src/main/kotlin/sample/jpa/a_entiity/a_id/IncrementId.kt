package sample.jpa.a_entiity.a_id

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.proxy.HibernateProxy

/*
 * 1. 영속화전에는 null이다.
 * 그 이후에는 null이 아니므로 getId는 단언문을 사용했다.
 *
 * 2. 영속화전에 ID가 사용되는 경우는 Entity로 Set을 만드는 경우다.
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
