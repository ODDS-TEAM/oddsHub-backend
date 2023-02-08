package team.odds.oddshub.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import team.odds.oddshub.entities.Course
import team.odds.oddshub.repositories.CourseRepository
import team.odds.oddshub.services.CourseService
import team.odds.oddshub.services.MailSenderService

@SpringBootTest
@AutoConfigureMockMvc
class CoursesControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var javaMailSenderService: MailSenderService

    @Nested
    inner class CoursesTests {
        private var courseRepository: CourseRepository = mockk()
        private val ccoCourse = Course(1, "CCO", "des", "image.png", "P'Roof")


        @Test
        fun notHaveAnyAvailableCourseForUser() {
            oddsHubNotHasAnyCourse()
            expectToNotHaveAnyCourse(whenGetAllCourses())
        }

        @Test
        fun onlyOneCourseAvailableForUser() {
            oddsHubHasOneCourse()
            expectToHaveOneCourse(whenGetAllCourses())
        }

        private fun oddsHubNotHasAnyCourse() {
            every { courseRepository.findAll()} returns listOf()
        }

        private fun oddsHubHasOneCourse() {
            every { courseRepository.findAll()} returns listOf(ccoCourse)
        }

        private fun whenGetAllCourses(): List<Course> = CoursesController(CourseService(courseRepository), javaMailSenderService).getAllCourses()

        private fun expectToHaveOneCourse(courses: List<Course>) {
            assert(courses.count() == 1)
            assert(courses[0] == ccoCourse)
        }

        private fun expectToNotHaveAnyCourse(courses: List<Course>) {
            assert(courses.isEmpty())
        }
    }

    @Nested
    inner class SendMailTest {
        @Test
        fun whenNoOneRegisterThisCourse_thenNoOneCannotReceiveEmail() {
            val courseWithNoParticipants = 1
            mockMvc.perform(MockMvcRequestBuilders.post("/courses/$courseWithNoParticipants/welcome"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
        }
        @Test
        fun whenHaveOneParticipantInThisCourse_thenParticipantShouldReceiveEmail(){
            val courseWithOneParticipant = 2

            every { javaMailSenderService.send(any(),any(),any()) } just runs

            mockMvc.perform(MockMvcRequestBuilders.post("/courses/$courseWithOneParticipant/welcome"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)

            verify {
                javaMailSenderService.send("newii@odds.team", "test email", "Lorem ipsum dolor sit amet [...]")
            }
        }
    }
}




