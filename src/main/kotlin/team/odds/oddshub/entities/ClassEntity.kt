package team.odds.oddshub.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "classes")
data class ClassEntity(
    @Id
    @GeneratedValue
    val id: Long,

    @Column(name = "course_id")
    val courseId: Long,

    @Temporal(TemporalType.TIMESTAMP)
    val startDate: Date? = Calendar.getInstance().time,

    @Temporal(TemporalType.TIMESTAMP)
    val endDate: Date? = Calendar.getInstance().time,
)
