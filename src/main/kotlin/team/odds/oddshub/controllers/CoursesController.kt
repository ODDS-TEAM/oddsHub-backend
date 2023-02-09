package team.odds.oddshub.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.entities.CourseEntity
import team.odds.oddshub.services.CourseService

@RestController
class CoursesController(
    val courseService: CourseService,
) {
    @GetMapping("/courses")
    fun getAllCourses(): List<CourseEntity> {
        return courseService.getAllCourses()
    }
}