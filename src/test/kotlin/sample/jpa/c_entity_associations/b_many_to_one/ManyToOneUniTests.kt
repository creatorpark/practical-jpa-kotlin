package sample.jpa.c_entity_associations.b_many_to_one

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
import sample.jpa.c_entity_associations.b_many_to_one.uni.PlayerUni
import sample.jpa.c_entity_associations.b_many_to_one.uni.TeamUni

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql(
    "classpath:table/associations/many-to-one-uni.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class ManyToOneUniTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}
    context("N:1 Unidirectional") {
        expect("PERSIST") {
            // Given
            val team = TeamUni("Team A")
            val player1 = PlayerUni("Player 1", 10)
            val player2 = PlayerUni("Player 2", 20)
            player1.team = team
            player2.team = team

            // When
            em.persist(team)
            em.persist(player1)
            em.persist(player2)
            em.flush()
            em.clear()

            // Then
            val savedTeam = em.find(TeamUni::class.java, team.id)
            savedTeam shouldNotBe null
            savedTeam.name shouldBe "Team A"
            val players = em.createQuery("SELECT p FROM PlayerUni p WHERE p.team.id = :teamId", PlayerUni::class.java)
                .setParameter("teamId", team.id)
                .resultList
            players.size shouldBe 2
            players.map { it.name }
                .containsAll(listOf("Player 1", "Player 2"))
        }

        expect("REMOVE") {
            // Given
            val team = TeamUni("Team A")
            val player1 = PlayerUni("Player 1", 10)
            val player2 = PlayerUni("Player 2", 20)
            player1.team = team
            player2.team = team
            // When
            em.persist(team)
            em.persist(player1)
            em.persist(player2)
            em.flush()
            em.clear()

            // When
            val players = em.createQuery("SELECT p FROM PlayerUni p WHERE p.team.id = :teamId", PlayerUni::class.java)
                .setParameter("teamId", team.id)
                .resultList
            val playerToRemove = players.firstOrNull()
            playerToRemove?.let { em.remove(it) }
            em.flush()
            em.clear()

            // Then
            val updatedPlayers =
                em.createQuery("SELECT p FROM PlayerUni p WHERE p.team.id = :teamId", PlayerUni::class.java)
                    .setParameter("teamId", team.id)
                    .resultList
            updatedPlayers.size shouldBe 1
            updatedPlayers.map { it.name }
                .contains(playerToRemove?.name) shouldBe false
        }
    }
})