package sample.jpa.b_entity_associations.a_one_to_one

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import sample.commons.P6SpyLogConfig
import sample.commons.toStringField
import sample.jpa.b_entity_associations.a_one_to_one.fk.AddressFk
import sample.jpa.b_entity_associations.a_one_to_one.fk.UserFk

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql(
    "classpath:table/associations/one-to-one-fk.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class OneToOneFKTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("1:1 FK 양방향 테스트") {
        expect("사용자, 주소 저장") {
            val address = AddressFk(street = "123 Street", city = "City")
            val user = UserFk(name = "John Doe", address)

            // 저장
            em.persist(user)
            em.flush()
            em.clear()

            // 저장된 사용자와 주소 가져오기
            val savedUser = em.find(UserFk::class.java, user.id)
            val savedAddress = em.find(AddressFk::class.java, address.id)

            // 검증
            savedUser shouldNotBe null
            savedAddress shouldNotBe null
            savedUser.address shouldNotBe null
            savedUser.address shouldBe savedAddress
        }

        expect("주소 remove 확인") {
            val address = AddressFk(street = "123 Street", city = "City")
            val user = UserFk(name = "John Doe", address)

            // 저장
            em.persist(user)
            em.flush()
            em.clear()

            // 저장된 사용자와 주소 가져오기
            val savedUser = em.find(UserFk::class.java, user.id)
            println(" SAVED USER ${savedUser.toStringField()}")
            println("-------")
            // 주소 제거
            savedUser.address = null
            em.merge(savedUser)

            em.flush()
            em.clear()
            println("-------")
            val userWithoutAddress = em.find(UserFk::class.java, savedUser?.id)

            userWithoutAddress shouldNotBe null
            userWithoutAddress.address shouldBe null
        }
    }
})