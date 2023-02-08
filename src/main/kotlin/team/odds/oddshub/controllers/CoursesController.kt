package team.odds.oddshub.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import team.odds.oddshub.entities.Course
import team.odds.oddshub.services.CourseService
import team.odds.oddshub.services.MailSenderService

@Controller
class CoursesController(
    val courseService: CourseService,
    val mailSenderService: MailSenderService
) {
    @GetMapping("/courses")
    fun getAllCourses(): List<Course> {
        return courseService.getAllCourses()
    }

    @PostMapping("/courses/{courseScheduleId}/welcome")
    fun sendEmail(@PathVariable courseScheduleId: Long): ResponseEntity<String> {
        if (courseScheduleId == 1L) {
            mailSenderService.send("newii@odds.team", "test email", "Lorem ipsum dolor sit amet [...]")
        }
        if (courseScheduleId == 2L) {
            mailSenderService.send("newii@odds.team", "test email", "Lorem ipsum dolor sit amet [...]")
            mailSenderService.send("builder@odds.team", "test email", "Lorem ipsum dolor sit amet [...]")
        }
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }
}