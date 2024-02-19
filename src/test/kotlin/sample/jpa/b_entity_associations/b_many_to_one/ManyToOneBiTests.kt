package sample.jpa.b_entity_associations.b_many_to_one

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
    "classpath:sql/table-b_associations-many-to-one-bi.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class ManyToOneBiTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("1:N 단방향 저장") {
        expect("저장 된 데이터 확인") {


        }
    }

})