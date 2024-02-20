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
import sample.jpa.b_entity_associations.a_one_to_one.pk.Address
import sample.jpa.b_entity_associations.a_one_to_one.pk.User

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql(
    "classpath:table/associations/one-to-one-pk.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class OneToOnePKTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("1:1 PK Bidirectional") {
        expect("PERSIST") {
            val address = Address(street = "123 Street", city = "City")
            val user = User(name = "John Doe", address = address)

            em.persist(user)
            em.flush()
            em.clear()

            val savedUser = em.find(User::class.java, user.id)
            val savedAddress = em.find(Address::class.java, address.id)

            savedUser shouldNotBe null
            savedAddress shouldNotBe null
        }
        expect("REMOVE") {
            val address = Address(street = "123 Street", city = "City")
            val user = User(name = "John Doe", address = address)

            em.persist(user)
            em.flush()
            em.clear()

            val savedUser = em.find(User::class.java, user.id)

            savedUser.address = null
            em.merge(savedUser)

            em.flush()
            em.clear()

            val userWithoutAddress = em.find(User::class.java, savedUser?.id)

            userWithoutAddress shouldNotBe null
            userWithoutAddress.address shouldBe null
        }
    }
})