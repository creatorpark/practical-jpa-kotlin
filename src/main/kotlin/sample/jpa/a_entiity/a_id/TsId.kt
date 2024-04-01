package sample.jpa.a_entiity.a_id

import io.hypersistence.tsid.TSID
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.proxy.HibernateProxy


/**
 * 기본적으로 사용되는 ID전략
 * https://github.com/vladmihalcea/hypersistence-tsid
 * JavaScript로 보낼 때 정밀도 오류가 있을 수 있으므로 String으로 보낸다.
 *
 * 참고자료
 * https://vladmihalcea.com/tsid-identifier-jpa-hibernate/
 * */
@MappedSuperclass
class TsId(
    id: Long = TSID.Factory.getTsid()
        .toLong()
) : AbstractId<Long>() {

    @Id
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("_id")
    val id: Long = id

    override fun getId(): Long = id

    override fun equalsId(obj: Any): Boolean {
        return if (obj is HibernateProxy) {
            id == obj.hibernateLazyInitializer.identifier
        } else {
            id == (obj as TsId).id
        }

    }
}
