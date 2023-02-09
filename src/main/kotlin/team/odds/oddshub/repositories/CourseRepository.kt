package team.odds.oddshub.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.odds.oddshub.entities.CourseEntity

@Repository
interface CourseRepository: JpaRepository<CourseEntity, Long> {
}