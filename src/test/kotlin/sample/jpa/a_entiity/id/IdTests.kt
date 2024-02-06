package sample.jpa.a_entiity.id

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.context.annotation.Import
import sample.commons.P6SpyLogConfig

@Import(P6SpyLogConfig::class)
open class IdTests : BehaviorSpec({
    val log = KotlinLogging.logger {}
})