package sample.jpa.b_entity_associations.d_many_to_many.bi

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.IncrementId


// 양방향으로 해야하는 이유
// 
@Entity
class Tag(
    val name: String
) : IncrementId() {

//    @ManyToMany
//    @JoinColumn(name = "POST_ID")
//    lateinit var post: Post

}


