package sample.jpa.b_entity_associations.c_one_to_many

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
    "classpath:sql/table-b_associations-one-to-n.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class OneToManyTests(
    @PersistenceContext
    val em: EntityManager
) : ExpectSpec({
    val log = KotlinLogging.logger {}

    context("1:N 단방향 저장") {
        expect("저장 된 데이터 확인") {
            val order = Order(name = "주문")
            val orderItem1 = OrderItem(name = "상품1", count = 1, price = 1000L)
            val orderItem2 = OrderItem(name = "상품2", count = 2, price = 2000L)
            order.orderItems.add(orderItem1)
            order.orderItems.add(orderItem2)

            em.persist(order)
            em.flush()
            em.clear()

            val findOrder = em.find(Order::class.java, order.id)
            findOrder.orderItems.size shouldBe 2
            findOrder.orderItems.contains(orderItem1) shouldBe true
            findOrder.orderItems.contains(orderItem2) shouldBe true

            findOrder.orderItems.clear()
            em.merge(findOrder)
            em.flush()

            val mergeOrder = em.find(Order::class.java, findOrder.id)
            mergeOrder.orderItems.size shouldBe 0
        }
    }
})