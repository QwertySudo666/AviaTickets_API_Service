package aviatickets_api.service

import aviatickets_api.models.User
import aviatickets_api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findById(id: Long): User {
        return userRepository.findById(id).get()
    }

    fun findByUsername(username: String): User {
        return userRepository.findByUsername(username)!!.get()
    }
}