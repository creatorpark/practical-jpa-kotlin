package sample.jpa.b_entity_associations.d_many_to_many.uni

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId

@Entity
class PostUni(
    var title: String,
    var contents: String,
) : TsId() {

//    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "team")
//    var tagUniList: MutableSet<TagUni> = mutableSetOf()
}