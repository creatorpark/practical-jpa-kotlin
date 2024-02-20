package sample.jpa.b_entity_associations.d_many_to_many

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import sample.commons.P6SpyLogConfig

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql(
    "classpath:table/associations/many-to-many-bi.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class ManyToManyBiTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("M:N 양방향 테스트") {
        expect("글, 댓글 저장") {

        }
        expect("댓글 REMOVE 확인") {

        }
    }
})