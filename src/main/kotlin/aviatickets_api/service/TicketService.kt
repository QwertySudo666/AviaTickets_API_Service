package aviatickets_api.service

import aviatickets_api.models.Ticket
import aviatickets_api.repository.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketService(private val ticketRepository: TicketRepository) {
    fun save(ticket: Ticket): Ticket {
        return ticketRepository.save(ticket)
    }

    fun findAllByUserId(userId: Long): List<Ticket> {
        return ticketRepository.findAllByUserId(userId)
    }

    fun findByIdAndUserId(id: Int, userId: Long): Ticket {
        return ticketRepository.findByIdAndUserId(id, userId)
    }

    fun deleteById(id: Int) {
        val ticket = ticketRepository.findById(id).get()
        ticketRepository.delete(ticket)
    }
}