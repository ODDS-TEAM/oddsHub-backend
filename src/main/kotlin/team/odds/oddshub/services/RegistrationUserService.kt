package team.odds.oddshub.services

import mu.KotlinLogging
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
    private val logger = KotlinLogging.logger {}

    fun saveRegistrationUser(registrationUserData: RegistrationUserPayload): Boolean {
        val classEntity = classRepository.getReferenceById(registrationUserData.classId)
        val courseEntity = courseRepository.getReferenceById(classEntity.courseId)
        val registrationUserEntityList = registrationUserRepository.getByClassId(registrationUserData.classId)

        logger.info { "Course ${courseEntity.id} # of registered ${registrationUserEntityList.size}/${courseEntity.quota} " }
        if (courseEntity.quota > registrationUserEntityList.size) {
            val registrationUserDataEntity = registrationUserData.transformToEntity()
            registrationUserRepository.save(registrationUserDataEntity)
                .also { logger.debug { "Registered Success ${it.id}" } }
            return true
        }
        return false
    }
}