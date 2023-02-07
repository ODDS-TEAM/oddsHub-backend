package team.odds.oddshub.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Course(
    @Id @GeneratedValue
    val id: Long? = null,
    val name: String?= null
)