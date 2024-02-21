package sample.jpa.b_entity_associations.c_one_to_many

import jakarta.persistence.*
import sample.jpa.a_entiity.id.IncrementId


/**
 * 그러나 FK는 N에 있는데 1에서 N을 관리 하려고 하니
 * 1에서 N의 리스트를 조작할 때 N의 FK를 UPDATE하는 쿼리가 또 실행 된다.
 *
 * 생성되는 쿼리가 실용적이지 않다.
 * 그래서 1:N 단방향은 실전에서 사용하지 않는다.
 *
 * 1:N의 양방향은 결국 N:1의 양방향과 예제가 같으므로 이를 사용한다.
 */
@Entity
@Table(name = "_order")
class Order(
    val name: String
) : IncrementId() {

    // FK가 OrderItem에 있으므로 Update가 발생한다.
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    val orderItems: MutableSet<OrderItem> = mutableSetOf()
}