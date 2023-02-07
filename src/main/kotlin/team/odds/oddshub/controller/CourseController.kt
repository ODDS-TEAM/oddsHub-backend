package team.odds.oddshub.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.entities.Course
import team.odds.oddshub.services.CourseService

@RestController
class CourseController constructor(val courseService: CourseService) {

    @GetMapping("/courses")
    fun all(): List<Course> {
        return courseService.all()
    }

}