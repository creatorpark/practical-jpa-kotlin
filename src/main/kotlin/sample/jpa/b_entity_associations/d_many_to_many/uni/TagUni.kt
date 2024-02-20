package sample.jpa.b_entity_associations.d_many_to_many.uni

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.IncrementId


@Entity
class TagUni(
    val name: String
) : IncrementId() {

//    @ManyToMany
//    @JoinColumn(name = "POST_ID")
//    lateinit var postUni: PostUni

}


