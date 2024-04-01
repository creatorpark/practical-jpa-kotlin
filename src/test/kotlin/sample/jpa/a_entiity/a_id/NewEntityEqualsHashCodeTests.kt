package sample.jpa.a_entiity.a_id

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import sample.commons.P6SpyLogConfig
import sample.jpa.a_entiity.IncrementIdEntity
import sample.jpa.a_entiity.TsIdEntity

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
class NewEntityEqualsHashCodeTests : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("IncrementId 엔티티 생성시 Equals 와 HashCode 테스트") {
        val entity1 = IncrementIdEntity("Alice")
        val entity2 = IncrementIdEntity("Bob")
        val entity3 = IncrementIdEntity("Alice")

        expect("equals") {
            (entity1 == entity1) shouldBe true
            (entity1 == entity2) shouldBe false
            (entity1 == entity3) shouldBe false
        }
        expect("hashCode") {
            entity1.hashCode() shouldBe entity3.hashCode()
            entity1.hashCode() shouldBe entity2.hashCode()
        }
    }

    context("TsID 엔티티 생성시 Equals 와 HashCode 테스트") {
        val entity1 = TsIdEntity("Alice")
        val entity2 = TsIdEntity("Bob")
        val entity3 = TsIdEntity("Alice")

        expect("equals") {
            (entity1 == entity1) shouldBe true
            (entity1 == entity2) shouldBe false
            (entity1 == entity3) shouldBe false
        }
        expect("hashCode") {
            entity1.hashCode() shouldBe entity3.hashCode()
            entity1.hashCode() shouldBe entity2.hashCode()
        }
    }
})