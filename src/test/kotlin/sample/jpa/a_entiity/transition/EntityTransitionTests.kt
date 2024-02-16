package sample.jpa.a_entiity.transition

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.hibernate.event.internal.EntityState
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import sample.commons.P6SpyLogConfig
import sample.jpa.a_entiity.IncrementIdEntity

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
class EntityTransitionTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    fun assertEntityState(entity: IncrementIdEntity, expectedState: EntityState) {
        when (expectedState) {
            EntityState.TRANSIENT -> {
                em.contains(entity) shouldBe false
                entity.id shouldBe null
            }

            EntityState.PERSISTENT -> {
                em.contains(entity) shouldBe true
                entity.id shouldNotBe null
                em.find(IncrementIdEntity::class.java, entity.id) shouldBe entity
                em.getReference(IncrementIdEntity::class.java, entity.id)
                    .equals(entity) shouldBe true
            }

            EntityState.DETACHED -> {
                em.contains(entity) shouldBe false
                entity.id shouldNotBe null
            }

            EntityState.DELETED -> {
                em.contains(entity) shouldBe false
                em.find(IncrementIdEntity::class.java, entity.id) shouldBe null
            }
        }
    }

    context("Entity State Transition TEST") {
        expect("영속화 전 후 상태") {
            var testEntity = IncrementIdEntity("Alice")
            assertEntityState(testEntity, EntityState.TRANSIENT)

            em.persist(testEntity)
            em.flush()

            assertEntityState(testEntity, EntityState.PERSISTENT)
        }

        expect("분리(detached) 상태") {
            var testEntity = IncrementIdEntity("Alice")
            em.persist(testEntity)
            em.flush()

            em.detach(testEntity)
            assertEntityState(testEntity, EntityState.DETACHED)
        }

        // https://vladmihalcea.com/jpa-persist-and-merge/
        // merge를 직접적으로 경험하는 경우는 거의 없다.
        expect("합병(merged) 상태") {
            var testEntity = IncrementIdEntity("Alice")
            assertEntityState(testEntity, EntityState.TRANSIENT)
            em.persist(testEntity)
            em.detach(testEntity)
            em.contains(testEntity) shouldBe false
            testEntity.name = "Stella"
            // INSERT가 아닌 UPDATE문이 실행 된다.
            var mergeEntity = em.merge(testEntity)
            mergeEntity.name shouldBe "Stella"
            em.flush()
            assertEntityState(mergeEntity, EntityState.PERSISTENT)
        }

        expect("삭제 상태") {
            var testEntity = IncrementIdEntity("Alice")
            em.persist(testEntity)
            em.flush()

            em.remove(testEntity)
            em.flush()
            assertEntityState(testEntity, EntityState.DELETED)
        }
    }
})