package aviatickets_api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    @JsonIgnore
    var userId: Long = 0
    var fromCity: String = ""
    var toCity: String = ""
    var planeNumber: String = ""
    var place: Int = 0
    var date: String = ""
    var time: String = ""
}