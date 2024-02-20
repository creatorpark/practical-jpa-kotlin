package sample.jpa.b_entity_associations.b_many_to_one

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import sample.commons.P6SpyLogConfig

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql(
    "classpath:table/associations/many-to-one-bi.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class ManyToOneBiTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("N:1 Bidirectional") {
        expect("PERSIST") {
            val team = Team("Team A")
            val player1 = Player("Player 1", 10)
            val player2 = Player("Player 2", 20)
            team.addPlayer(player1)
            team.addPlayer(player2)
            // When
            em.persist(team)
            em.flush()
            em.clear()

            // Then
            val savedTeam = em.find(Team::class.java, team.id)
            savedTeam shouldNotBe null
            savedTeam.name shouldBeEqual "Team A"
            savedTeam.players.size shouldBe 2
            savedTeam.players.map { it.name }
                .containsAll(listOf("Player 1", "Player 2"))
        }
        expect("REMOVE") {
            // Given
            val team = Team("Team A")
            val player1 = Player("Player 1", 10)
            val player2 = Player("Player 2", 20)
            team.addPlayer(player1)
            team.addPlayer(player2)

            em.persist(team)
            em.flush()
            em.clear()

            val savedTeam = em.find(Team::class.java, team.id)

            // When
            val playerToRemove = savedTeam?.players?.firstOrNull()
            playerToRemove?.let { savedTeam.removePlayer(it) }
            em.flush()
            em.clear()

            // Then
            val updatedTeam = em.find(Team::class.java, team.id)
            updatedTeam shouldNotBe null
            updatedTeam?.players?.size shouldBe 1
            updatedTeam?.players?.map { it.name }
                ?.contains(playerToRemove?.name) shouldBe false
        }
    }
})