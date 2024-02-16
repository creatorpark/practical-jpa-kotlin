package sample.jpa.b_entity_associations.a_one_to_one

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import sample.commons.P6SpyLogConfig

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
class OneToOnePKTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}


})