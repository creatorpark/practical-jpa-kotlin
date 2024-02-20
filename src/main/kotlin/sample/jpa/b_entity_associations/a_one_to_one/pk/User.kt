package sample.jpa.b_entity_associations.a_one_to_one.pk

import jakarta.persistence.*
import sample.jpa.a_entiity.id.IncrementId

/**
 * JPA 1:1 PK는 양방향을 사용한다.
 * FK는 단방향이 되는데, PK는 MapsID를 사용해야 양방향으로 인식한다.
 *
 * https://vladmihalcea.com/change-one-to-one-primary-key-column-jpa-hibernate/
 *
 * just adding the JPA @OneToOne annotation in the child entity
 * does not render a true one-to-one table relationship
 * since a separate Foreign Key column will be used.
 * Only when adding the @MapsId annotation will the JPA one-to-one association
 * map to a real one-to-one table relationship.
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

    @OneToOne(
        mappedBy = "user", cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    @PrimaryKeyJoinColumn
    var address: Address? = address
        set(value) {
            field = value
            value?.user = this
        }
}