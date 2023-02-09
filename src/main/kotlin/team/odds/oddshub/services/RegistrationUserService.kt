package team.odds.oddshub.services

import org.springframework.stereotype.Service
import team.odds.oddshub.entities.RegistrationUserEntity
import team.odds.oddshub.entities.dto.RegistrationUserPayload
import team.odds.oddshub.repositories.RegistrationUserRepository

@Service
class RegistrationUserService(
        val registrationUserRepository: RegistrationUserRepository) {
    fun saveRegistrationUser(registrationUserData: RegistrationUserPayload) {
        val registrationUserEntity = RegistrationUserEntity(
                title = registrationUserData.title,
                firstName = registrationUserData.firstName,
                lastName = registrationUserData.lastName,
                email = registrationUserData.email,
                phone = registrationUserData.phone,
                classId = registrationUserData.classId
        )
        registrationUserRepository.save(registrationUserEntity)
    }
}