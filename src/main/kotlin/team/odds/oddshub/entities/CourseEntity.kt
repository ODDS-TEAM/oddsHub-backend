package team.odds.oddshub.entities

import jakarta.persistence.*

@Entity
@Table(name = "courses")
data class CourseEntity(
        @Id
        @GeneratedValue
        val id: Long,

        val name: String,

        val description: String,

        val image: String,

        val instructor: String,

        val quota: Int,

        val price: Int
)