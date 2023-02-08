package team.odds.oddshub.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.odds.oddshub.entities.RegistrationUserEntity

@Repository
interface RegistrationUserRepository: JpaRepository<RegistrationUserEntity, Long> {
    fun getByCourseScheduleId(courseScheduleId: Long): List<RegistrationUserEntity>
}