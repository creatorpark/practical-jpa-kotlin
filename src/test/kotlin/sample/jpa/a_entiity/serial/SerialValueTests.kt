package sample.jpa.a_entiity.serial

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import sample.commons.P6SpyLogConfig

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
class SerialValueTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    // the referenced field must be serializable when we use referencedColumnName from the @JoinColumn annotation.
    context("Entity에 Serializable을 해야하는 경우") {
        expect("1:N 관계") {
            // https://www.baeldung.com/jpa-entities-serializable
            // AbstractId의
        }

    }
})