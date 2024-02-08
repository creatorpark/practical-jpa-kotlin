package sample.jpa.a_entiity.id

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.proxy.HibernateProxy


// 3rd-Party 데이터 입수할 때 데이터들의 Key가 String 일 수 있음.
@MappedSuperclass
class StringId(
    id: String
) : AbstractId<String>() {

    @Id
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("_id")
    val id: String = id

    override fun getId(): String = id

    override fun idCompare(obj: Any): Boolean {
        return if (obj is HibernateProxy) {
            id == obj.hibernateLazyInitializer.identifier
        } else {
            id == (obj as StringId).id
        }
    }

}
