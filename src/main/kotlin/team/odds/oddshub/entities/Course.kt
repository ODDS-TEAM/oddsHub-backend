package team.odds.oddshub.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Course(
    @Id @GeneratedValue
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val instructor: String = "",
)