package team.odds.oddshub.entities.dto

import team.odds.oddshub.entities.RegistrationUserEntity
import java.util.*

data class RegistrationUserPayload(
    val title: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val classId: Long,
) {
    fun transformToEntity(): RegistrationUserEntity {
        return RegistrationUserEntity(
            UUID.randomUUID(),
            this.title,
            this.firstName,
            this.lastName,
            this.email,
            this.phone,
            this.classId
        )
    }
}