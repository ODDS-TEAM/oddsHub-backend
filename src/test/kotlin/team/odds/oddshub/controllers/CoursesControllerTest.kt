package team.odds.oddshub.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.Called
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import team.odds.oddshub.entities.Course
import team.odds.oddshub.entities.RegistrationUserEntity
import team.odds.oddshub.repositories.CourseRepository
import team.odds.oddshub.repositories.RegistrationUserRepository
import team.odds.oddshub.services.CourseService
import team.odds.oddshub.services.MailSenderService

@SpringBootTest
@AutoConfigureMockMvc
class CoursesControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var javaMailSenderService: MailSenderService

    @MockkBean
    lateinit var registrationUserRepository: RegistrationUserRepository

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
            every { courseRepository.findAll() } returns listOf()
        }

        private fun oddsHubHasOneCourse() {
            every { courseRepository.findAll() } returns listOf(ccoCourse)
        }

        private fun whenGetAllCourses(): List<Course> =
            CoursesController(CourseService(courseRepository), javaMailSenderService, registrationUserRepository).getAllCourses()

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

        @BeforeEach
        fun beforeEach() {
            every { javaMailSenderService.send(any(), any(), any()) } just runs
        }

        @Test
        fun `when no one register this course then no one cannot receive email`() {
            val courseWithNoParticipants = 0

            givenParticipantOfCourse(0, emptyList())
            mockMvc.perform(MockMvcRequestBuilders.post("/courses/$courseWithNoParticipants/welcome"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)


            verify { javaMailSenderService wasNot Called }
        }

        @Test
        fun `when have one participant in this course then participant should receive email`() {
            val courseWithOneParticipant = 1L
            givenParticipantOfCourse(courseWithOneParticipant, listOf("newii@odds.team"))
            mockMvc.perform(MockMvcRequestBuilders.post("/courses/$courseWithOneParticipant/welcome"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)

            verify {
                javaMailSenderService.send("newii@odds.team", "test email", "Lorem ipsum dolor sit amet [...]")
            }
        }

        private fun givenParticipantOfCourse(courseWithOneParticipant: Long, participantList: List<String>) {
            val participantListEntity =  participantList.mapIndexed { index, email ->
                RegistrationUserEntity(
                    index.toLong(),
                    "Miss",
                    "Newoo",
                    "sad boy",
                    email,
                    "09090909090",
                    courseWithOneParticipant
                ) }

            every { registrationUserRepository.getByCourseScheduleId(courseWithOneParticipant) } returns participantListEntity
        }
    }
}




