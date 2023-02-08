package team.odds.oddshub.controllers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.entities.Course
import team.odds.oddshub.services.CourseService

@RestController
class CoursesController(
    val courseService: CourseService
) {

    @GetMapping("/courses")
    fun getAllCourses(): List<Course> {
        return courseService.getAllCourses()
    }

    @PostMapping("/courses/{courseScheduleId}/welcome")
    fun sendEmail(@PathVariable courseScheduleId: Long): ResponseEntity<String> {
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }
}