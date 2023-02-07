package team.odds.oddshub.controllers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CoursesController {
    @PostMapping("/courses/{courseScheduleId}/welcome")
    fun sendEmail(@PathVariable courseScheduleId: Long): ResponseEntity<String> {
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }
}