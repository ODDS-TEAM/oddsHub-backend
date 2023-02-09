package team.odds.oddshub.entities

import jakarta.persistence.*

@Entity
@Table(name = "courses")
data class CourseEntity(
        @Id
        @GeneratedValue
        val id: Long,

        val name: String,

        @Column(columnDefinition="TEXT")
        val description: String,

        @Column(columnDefinition="TEXT")
        val image: String,

        val instructor: String
)