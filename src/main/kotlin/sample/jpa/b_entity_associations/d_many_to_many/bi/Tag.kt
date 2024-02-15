package sample.jpa.b_entity_associations.d_many_to_many.bi

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import sample.jpa.a_entiity.id.TsId


// 양방향으로 해야하는 이유
// 
@Entity
class Tag(
    val name: String
) : TsId() {

    @ManyToMany
    @JoinColumn(name = "POST_ID")
    lateinit var post: Post

}


