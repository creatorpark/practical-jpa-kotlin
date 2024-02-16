package sample.jpa.b_entity_associations.d_many_to_many

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import sample.commons.P6SpyLogConfig

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
class ManyToManyBiDiTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}


})