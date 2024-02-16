package sample.jpa.b_entity_associations.c_one_to_many.bi

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import sample.jpa.a_entiity.id.TsId

/**
 * 1:N은 연관 관계를 1쪽에서 관리하겠다는 뜻이다.
 * 이 모델은 권장하지 않으며, 한다면 양방향으로 해야한다.
 * 양방향으로 하지 않으면 UPDATE문이 발생한다.
 *
 */
@Entity
class Order(
    val name: String
) : TsId() {

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var orderItems: MutableSet<OrderItem> = mutableSetOf()
}