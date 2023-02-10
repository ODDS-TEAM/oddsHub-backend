package team.odds.oddshub.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "registration_users")
data class RegistrationUserEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    val title: String = "",

    val firstName: String = "",

    val lastName: String = "",

    val email: String = "",

    val phone: String = "",

    @Column(name = "class_id")
    val classId: Long = 0,
)