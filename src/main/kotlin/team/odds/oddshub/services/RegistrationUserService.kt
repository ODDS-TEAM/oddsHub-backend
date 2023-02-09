package team.odds.oddshub.services

import org.springframework.stereotype.Service
import team.odds.oddshub.entities.dto.RegistrationUserPayload
import team.odds.oddshub.repositories.ClassRepository
import team.odds.oddshub.repositories.CourseRepository
import team.odds.oddshub.repositories.RegistrationUserRepository

@Service
class RegistrationUserService(
        val registrationUserRepository: RegistrationUserRepository,
    val classRepository: ClassRepository,
    val courseRepository: CourseRepository,
) {
    fun saveRegistrationUser(registrationUserData: RegistrationUserPayload): Boolean {
        val classEntity = classRepository.getReferenceById(registrationUserData.classId)
        val courseEntity = courseRepository.getReferenceById(classEntity.courseId)
        val registrationUserEntityList = registrationUserRepository.getByClassId(registrationUserData.classId)
        if (courseEntity.quota > registrationUserEntityList.size) {
            val registrationUserDataEntity = registrationUserData.transformToEntity()
            registrationUserRepository.save(registrationUserDataEntity)
            return true
        }
        return false
    }
}