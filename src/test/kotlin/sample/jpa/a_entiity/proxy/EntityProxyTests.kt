package sample.jpa.a_entiity.proxy

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.hibernate.proxy.HibernateProxy
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import sample.commons.P6SpyLogConfig
import sample.jpa.a_entiity.IncrementIdEntity

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql("classpath:table/a_entity.sql")
class EntityProxyTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("Proxy Test") {
        expect("with getReference") {
            val entity = IncrementIdEntity(name = "Test Entity")

            em.persist(entity)
            em.flush()
            em.clear()

            val referenceEntity = em.getReference(IncrementIdEntity::class.java, entity.id)
            (referenceEntity is HibernateProxy) shouldBe true
            (entity == referenceEntity) shouldBe true
        }
    }
})