package team.odds.oddshub.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class RegistrationUserEntity(
    @Id @GeneratedValue
    val id: Long? = 0,
    val title: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val courseScheduleId: Long,
)

data class RegistrationUserPayload(
    val title: String ,
    val firstName: String ,
    val lastName: String ,
    val email: String ,
    val phone: String ,
    val courseScheduleId: Long ,
)