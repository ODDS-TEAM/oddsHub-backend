package team.odds.oddshub.services

import org.springframework.stereotype.Service
import team.odds.oddshub.entities.RegistrationUser
import team.odds.oddshub.repositories.RegistrationUserRepository
import team.odds.oddshub.entities.RegistrationUserPayload

@Service
class RegistrationUserService(
        val registrationUserRepository: RegistrationUserRepository) {
    fun saveRegistrationUser(registrationUserData: RegistrationUserPayload) {
        val registrationUser = RegistrationUser(
                title = registrationUserData.title,
                firstName = registrationUserData.firstName,
                lastName = registrationUserData.lastName,
                email = registrationUserData.email,
                phone = registrationUserData.phone,
                courseScheduleId = registrationUserData.courseScheduleId
        )
        registrationUserRepository.save(registrationUser)
    }
}