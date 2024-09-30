package sample.jpa.c_entity_associations.d_many_to_many

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import sample.commons.P6SpyLogConfig

@DataJpaTest(showSql = false)
@Import(P6SpyLogConfig::class)
@Sql(
    "classpath:table/associations/many-to-many.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class ManyToManyBiTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("M:N Bidirectional") {
        expect("PERSIST") {
            val tag1 = Tag(name = "Tag1")
            val tag2 = Tag(name = "Tag2")

            val post = Post(title = "Post1", contents = "Contents1")

            post.addTag(tag1)
            post.addTag(tag2)

            em.persist(post)
            em.flush()
            em.clear()

            val savedPost = em.find(Post::class.java, post.id)
            val savedTag1 = em.find(Tag::class.java, tag1.id)
            val savedTag2 = em.find(Tag::class.java, tag2.id)

            savedPost.tags.size shouldBe 2
            savedTag1.posts.contains(savedPost) shouldBe true
            savedTag2.posts.contains(savedPost) shouldBe true
        }

        expect("REMOVE") {
            val tag1 = Tag(name = "Tag1")
            val tag2 = Tag(name = "Tag2")

            val post = Post(title = "Post1", contents = "Contents1")

            post.addTag(tag1)
            post.addTag(tag2)

            em.persist(post)
            em.flush()
            em.clear()

            val savedPost = em.find(Post::class.java, post.id)
            val savedTag1 = em.find(Tag::class.java, tag1.id)

            savedPost.removeTag(savedTag1)
            em.flush()
            em.clear()

            val updatedPost1 = em.find(Post::class.java, savedPost.id)
            val updatedTag1 = em.find(Tag::class.java, savedTag1.id)

            updatedPost1.tags.size shouldBe 1
            updatedTag1.posts.size shouldBe 0
        }
    }
})