package team.odds.oddshub.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.model.Course

@RestController
class CourseController {

    @GetMapping("/courses")
    fun all(): List<Course> {
        return listOf(Course("CCO"));
    }

}