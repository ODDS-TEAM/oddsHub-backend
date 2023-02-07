package team.odds.oddshub.services

import org.springframework.stereotype.Service
import team.odds.oddshub.entities.Course
import team.odds.oddshub.repositories.CourseRepository

@Service
class CourseService constructor(val courseRepository: CourseRepository) {
    fun all(): List<Course> {
        return courseRepository.findAll()
    }
}