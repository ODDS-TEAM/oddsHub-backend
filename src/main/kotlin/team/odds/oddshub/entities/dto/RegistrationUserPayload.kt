package team.odds.oddshub.entities.dto

data class RegistrationUserPayload(
    val title: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val classId: Long,
)