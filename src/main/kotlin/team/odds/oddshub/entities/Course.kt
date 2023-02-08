package team.odds.oddshub.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Course(
        @Id @GeneratedValue
        val id: Long = 0,
        val name: String = "",
        @Column(columnDefinition="TEXT") val description: String = "",
        @Column(columnDefinition="TEXT") val image: String = "",
        val instructor: String = ""
)