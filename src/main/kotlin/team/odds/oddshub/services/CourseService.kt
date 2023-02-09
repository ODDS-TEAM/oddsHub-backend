package team.odds.oddshub.services

import org.springframework.stereotype.Service
import team.odds.oddshub.entities.CourseEntity
import team.odds.oddshub.repositories.CourseRepository

@Service
class CourseService(
    val courseRepository: CourseRepository) {
    fun getAllCourses(): List<CourseEntity> {
        return courseRepository.findAll()
    }
}