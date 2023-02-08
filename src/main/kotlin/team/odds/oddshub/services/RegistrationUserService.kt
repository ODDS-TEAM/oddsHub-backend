package team.odds.oddshub.services

import org.springframework.stereotype.Service
import team.odds.oddshub.entities.RegistrationUser
import team.odds.oddshub.repositories.RegistrationUserRepository

@Service
class RegistrationUserService(
    val registrationUserRepository: RegistrationUserRepository) {
    fun saveRegistrationUser(registrationUserData:Map<String,String>){
        val registrationUser = RegistrationUser(
        title = registrationUserData.get("title").orEmpty(),
        firstName = registrationUserData.get("firstName").orEmpty(),
        lastName = registrationUserData.get("lastName").orEmpty(),
        email = registrationUserData.get("email").orEmpty(),
        phone = registrationUserData.get("phone").orEmpty(),
        courseScheduleId = registrationUserData.get("courseScheduleId").orEmpty().toInt()
        )
        registrationUserRepository.save(registrationUser)
    }
}