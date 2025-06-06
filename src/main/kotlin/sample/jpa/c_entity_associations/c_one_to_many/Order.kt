package sample.jpa.c_entity_associations.c_one_to_many

import jakarta.persistence.*
import sample.jpa.a_entiity.a_id.IncrementId


/*
1:N 단방향은 생성되는 쿼리가 실용적이지 않아서 실전에서 사용되지 않는다.

FK가 N에 있어서 1에서 N을 관리하는데
1에서 N을 조작할 때 N의 FK를 UPDATE하는 쿼리가 추가로 실행 된다.

1:N의 양방향은 결국 N:1의 양방향과 예제가 같으므로 이를 사용한다.
 */
@Entity
@Table(name = "_order")
class Order(
    val name: String
) : IncrementId() {
    /*
        FK가 OrderItem에 있으므로 Update가 발생한다.
        If the join is for a unidirectional OneToMany mapping using a foreign key mapping strategy,
        the foreign key is in the table of the target entity.
     */
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    val orderItems: MutableSet<OrderItem> = mutableSetOf()
}