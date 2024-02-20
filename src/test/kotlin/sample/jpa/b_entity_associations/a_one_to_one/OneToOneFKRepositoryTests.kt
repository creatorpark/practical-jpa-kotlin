package sample.jpa.b_entity_associations.a_one_to_one

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import sample.commons.P6SpyLogConfig
import sample.jpa.b_entity_associations.a_one_to_one.fk.AddressFk
import sample.jpa.b_entity_associations.a_one_to_one.fk.UserFk
import sample.jpa.b_entity_associations.a_one_to_one.fk.UserFkRepository

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql(
    "classpath:table/associations/one-to-one-fk.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class OneToOneFKRepositoryTests(
    val repository: UserFkRepository
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("1:1 FK Bidirectional") {
        expect("PERSIST") {
            val address = AddressFk(street = "123 Street", city = "City")
            val user = UserFk(name = "John Doe", address)

            val savedUser = repository.saveAndFlush(user)

            savedUser shouldNotBe null
            savedUser.address shouldNotBe null
            savedUser.name shouldBe user.name
        }

        expect("REMOVE") {
            val address = AddressFk(street = "123 Street", city = "City")
            val user = UserFk(name = "John Doe", address)

            val savedUser = repository.saveAndFlush(user)

            savedUser.address = null
            repository.saveAndFlush(savedUser)

            val userWithOutAddress = repository.findById(savedUser.id!!)
                .get()

            userWithOutAddress shouldNotBe null
            userWithOutAddress.address shouldBe null

        }
    }
})