package aviatickets_api.repository

import aviatickets_api.models.Ticket
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : CrudRepository<Ticket, Int> {
    fun findAllByUserId(userId: Long): List<Ticket>
    fun findByIdAndUserId(id: Int, userId: Long): Ticket
}