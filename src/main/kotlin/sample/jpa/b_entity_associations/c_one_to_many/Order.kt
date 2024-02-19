package sample.jpa.b_entity_associations.c_one_to_many

import jakarta.persistence.*
import sample.jpa.a_entiity.id.IncrementId


/**
 * 1:N은 연관 관계를 1쪽에서 관리 하겠다는 뜻이다. 그러나 FK는 N에 있기 때문에
 * JPA에서 생성되는 쿼리가 실용적이지 않다. 그래서 ManyToOne 처리하게 된다.
 *
 * 1:N의 경우 N의 리스트를 조작할 경우 N의 FK를 관리해야하기 때문에
 * N의 FK를 UPDATE하는 쿼리가 또 실행 된다.
 *
 * 양방향 매핑은 존재X
 * 실제적으로는 ManyToOne 방법임.
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 */
@Entity
@Table(name = "_order")
class Order(
    val name: String
) : IncrementId() {

    // FK가 OrderUniItem에 있으므로 Update가 발생한다.
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    val orderItems: MutableSet<OrderItem> = mutableSetOf()
}