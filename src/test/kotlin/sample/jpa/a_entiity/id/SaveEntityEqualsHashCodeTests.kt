package sample.jpa.a_entiity.id

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import sample.commons.P6SpyLogConfig
import sample.commons.toStringField
import sample.jpa.a_entiity.IncrementIdEntity
import sample.jpa.a_entiity.IncrementIdEntityRepository

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql("classpath:sql/table-a_entity.sql")
class SaveEntityEqualsHashCodeTests(
    val repository: IncrementIdEntityRepository
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("Entity 저장 전후의 ID 및 Equals 테스트") {
        val entity1 = IncrementIdEntity("Alice")

        expect("저장전에 id를 조회하면 예외가 발생한다.") {
            shouldThrow<NullPointerException> {
                entity1.getId()
            }
        }

        expect("저장후에 id를 조회하면 id가 조회된다.") {
            repository.save(entity1)
            entity1.id shouldNotBe null
            entity1.name shouldBe "Alice"
            log.info { entity1.toStringField() }
        }
    }
})