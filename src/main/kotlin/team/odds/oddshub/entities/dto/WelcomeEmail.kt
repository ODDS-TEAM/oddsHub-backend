package team.odds.oddshub.entities.dto

data class WelcomeEmail(
    val to: String,
    val subject: String,
    val name: String
)