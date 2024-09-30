package sample.jpa.c_entity_associations.b_many_to_one.uni

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerUniRepository : CrudRepository<PlayerUni, Long>
