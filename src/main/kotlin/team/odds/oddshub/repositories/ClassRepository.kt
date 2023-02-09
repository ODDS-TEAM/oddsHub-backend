package team.odds.oddshub.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.odds.oddshub.entities.ClassEntity
import java.util.Optional

@Repository
interface ClassRepository: JpaRepository<ClassEntity, Long> {
    fun findByCourseId(courseId: Long): Optional<ClassEntity>
}