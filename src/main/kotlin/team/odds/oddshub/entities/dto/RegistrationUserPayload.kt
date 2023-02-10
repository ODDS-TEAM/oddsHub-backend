package team.odds.oddshub.entities.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import team.odds.oddshub.entities.RegistrationUserEntity
import java.util.*

data class RegistrationUserPayload(
    @field:NotEmpty val title: String,
    @field:NotEmpty val firstName: String,
    @field:NotEmpty val lastName: String,
    @field:NotEmpty @field:Email val email: String,
    @field:NotEmpty val phone: String,
    @field:NotNull val classId: Long,
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