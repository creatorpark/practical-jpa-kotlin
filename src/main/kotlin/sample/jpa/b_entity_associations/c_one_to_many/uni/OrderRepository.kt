package sample.jpa.b_entity_associations.c_one_to_many.uni

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Long>
