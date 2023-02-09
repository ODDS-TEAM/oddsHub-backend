package team.odds.oddshub.entities.dto

import java.util.*

data class CoursePayload(
    val id: Long,
    val name: String,
    val description: String,
    val instructor: String,
    val price: Int,
    val startDate: Date
)