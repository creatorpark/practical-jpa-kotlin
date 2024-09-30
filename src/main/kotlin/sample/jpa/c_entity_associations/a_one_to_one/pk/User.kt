package sample.jpa.c_entity_associations.a_one_to_one.pk

import jakarta.persistence.*
import sample.jpa.a_entiity.a_id.IncrementId

/**
 * JPA 1:1 PK는 양방향을 사용한다.
 * FK는 단방향이 되는데, PK는 MapsID를 사용해야 양방향으로 인식한다.
 *
 * Child Entity의 이름을 변경하고 싶으면 @JoinColumn을 사용해야한다.ㅋㅋㅋ
 * https://vladmihalcea.com/change-one-to-one-primary-key-column-jpa-hibernate/
 */
@Table(name = "_user")
@Entity
class User(
    val name: String,
    address: Address
) : IncrementId() {

    init {
        address.user = this
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @PrimaryKeyJoinColumn
    var address: Address? = address
        set(value) {
            field = value
            value?.user = this
        }
}