package team.odds.oddshub.entities

import jakarta.persistence.*

@Entity
@Table(name = "courses")
data class CourseEntity(
        @Id
        @GeneratedValue
        val id: Long = 0,

        val name: String = "",

        val description: String = "",

        val image: String = "",

        val instructor: String = "",

        val quota: Int = 0,

        val price: Int = 0
)