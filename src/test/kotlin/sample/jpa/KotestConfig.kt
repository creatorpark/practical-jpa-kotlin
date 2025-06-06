package sample.jpa

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

/**
 * Kotest 전역 설정하기
 * 거의  Spring Extension 추가가 목적임.
 */
class KotestConfig : AbstractProjectConfig() {
    val log = KotlinLogging.logger {}

    init {
        log.info { "------- KOTEST CONFIG" }
    }

    // 추가로 기능을 추가해서 기본 TestSet을 만든다.
    override fun extensions() = listOf(SpringExtension)
}
