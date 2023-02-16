package team.odds.oddshub.controllers

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import team.odds.oddshub.entities.ClassEntity
import team.odds.oddshub.entities.CourseEntity
import team.odds.oddshub.entities.dto.CoursePayload
import team.odds.oddshub.repositories.ClassRepository
import team.odds.oddshub.repositories.CourseRepository
import team.odds.oddshub.services.CourseService
import java.util.*

class CoursesControllerTest {

    private var courseRepository: CourseRepository = mockk()
    private var classRepository: ClassRepository = mockk()
    private val ccoCourse = CourseEntity(1, "CCO", "des", "image.png", "P'Roof", 30, 40000)
    private val ccoClass = ClassEntity(1, 1, Date(), Date())
    private val ccoPayload = CoursePayload(1, "CCO", "des","image.png", "P'Roof", 40000, Date())

    @Test
    fun `Not have any available course for user`() {
        oddsHubNotHasAnyCourse()
        expectToNotHaveAnyCourse(whenGetAllCourses())
    }

    @Test
    fun `Only one course available for user`() {
        oddsHubHasOneCourse()
        expectToHaveOneCourse(whenGetAllCourses())
    }

    private fun oddsHubNotHasAnyCourse() {
        every { courseRepository.findAll() } returns listOf()
        every { classRepository.findByCourseId(any()) } returns Optional.empty()
    }

    private fun oddsHubHasOneCourse() {
        every { courseRepository.findAll() } returns listOf(ccoCourse)
        every { classRepository.findByCourseId(any()) } returns Optional.of(ccoClass)
    }

    private fun whenGetAllCourses(): List<CoursePayload> =
        CoursesController(
            CourseService(courseRepository, classRepository)
        ).getAllCourses()

    private fun expectToHaveOneCourse(courses: List<CoursePayload>) {
        assert(courses.count() == 1)
        assert(courses[0] == ccoPayload)
    }

    private fun expectToNotHaveAnyCourse(courses: List<CoursePayload>) {
        assert(courses.isEmpty())
    }
}




