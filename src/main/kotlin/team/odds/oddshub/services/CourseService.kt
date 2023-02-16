package team.odds.oddshub.services

import org.springframework.stereotype.Service
import team.odds.oddshub.entities.dto.CoursePayload
import team.odds.oddshub.repositories.ClassRepository
import team.odds.oddshub.repositories.CourseRepository

@Service
class CourseService(
    val courseRepository: CourseRepository,
    val classRepository: ClassRepository) {
    fun getAllCourses(): List<CoursePayload> {
        val courses = courseRepository.findAll()
        return courses.map {
            val class2 = classRepository.findByCourseId(it.id).get()
            CoursePayload(
                it.id,
                class2.id,
                it.name,
                it.description,
                it.image,
                it.instructor,
                it.price,
                class2.startDate!!
            )
        }.toList()
    }
}