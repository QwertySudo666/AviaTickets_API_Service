package aviatickets_api.controllers

import aviatickets_api.models.Ticket
import aviatickets_api.models.User
import aviatickets_api.service.TicketService
import aviatickets_api.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/tickets")
class TicketController(private val ticketService: TicketService, val userService: UserService) {
    //Add new ticket
    @PostMapping("save")
    fun addTicket(@RequestBody requestTicket: Ticket, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        val userFromDB = getUserFromDB(jwt)
        requestTicket.userId = userFromDB.id
        val ticket = ticketService.save(requestTicket)
        //userFromDB.tickets.add(ticket)
        return ResponseEntity.ok("success")
    }

    @GetMapping("")
    fun getTickets(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        val userFromDB = getUserFromDB(jwt)
        val tickets = ticketService.findAllByUserId(userFromDB.id)
        return ResponseEntity.ok(tickets)
    }

    @GetMapping("{ticketId}")
    fun getTicket(@PathVariable ticketId: Int, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        val userFromDB = getUserFromDB(jwt)
        val ticket = ticketService.findByIdAndUserId(ticketId, userFromDB.id)
        return ResponseEntity.ok(ticket)
    }

    @DeleteMapping("{ticketId}")
    fun deleteTicket(@PathVariable ticketId: Int, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        ticketService.deleteById(ticketId)
        return ResponseEntity.ok("success")
    }

    private fun getUserFromDB(jwt: String?): User {
        val userDetails = SecurityContextHolder.getContext().authentication
            .principal as UserDetails
        val username = userDetails.username
        return userService.findByUsername(username)
    }
}