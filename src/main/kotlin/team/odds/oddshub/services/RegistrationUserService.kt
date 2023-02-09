package team.odds.oddshub.services

import org.springframework.stereotype.Service
import team.odds.oddshub.entities.dto.RegistrationUserPayload
import team.odds.oddshub.repositories.RegistrationUserRepository

@Service
class RegistrationUserService(
        val registrationUserRepository: RegistrationUserRepository) {
    fun saveRegistrationUser(registrationUserData: RegistrationUserPayload) {
        val registrationUserEntity = registrationUserData.transformToEntity()
        registrationUserRepository.save(registrationUserEntity)
    }
}