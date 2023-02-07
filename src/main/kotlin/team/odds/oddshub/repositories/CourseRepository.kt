package team.odds.oddshub.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.odds.oddshub.model.Course

@Repository
interface CourseRepository: JpaRepository<Course, Long> {
}