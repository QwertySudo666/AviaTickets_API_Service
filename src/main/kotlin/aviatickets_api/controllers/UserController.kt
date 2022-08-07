package aviatickets_api.controllers

import aviatickets_api.models.User
import aviatickets_api.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users/")
class UserController(private val userService: UserService) {
    @GetMapping("user")
    fun getUser(@CookieValue("jwt") jwt: String?): ResponseEntity<User> {
        val userFromDB = getUserFromDB(jwt)
        return ResponseEntity.ok().body(userFromDB)
    }

    private fun getUserFromDB(jwt: String?): User {
        val userDetails = SecurityContextHolder.getContext().authentication
            .principal as UserDetails
        val username = userDetails.username
        return userService.findByUsername(username)
    }
}