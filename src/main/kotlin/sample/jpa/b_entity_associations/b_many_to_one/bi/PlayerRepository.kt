package sample.jpa.b_entity_associations.b_many_to_one.bi

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : CrudRepository<Player, Long>
