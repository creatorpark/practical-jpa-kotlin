package sample.jpa.b_entity_associations.d_many_to_many

import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import sample.jpa.a_entiity.a_id.IncrementId


@Entity
class Tag(
    val name: String
) : IncrementId() {

    @ManyToMany(mappedBy = "tags")
    val posts: MutableSet<Post> = mutableSetOf()
}


