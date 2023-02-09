package team.odds.oddshub.controllers

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import team.odds.oddshub.entities.CourseEntity
import team.odds.oddshub.repositories.CourseRepository
import team.odds.oddshub.services.CourseService

class CoursesControllerTest {

    private var courseRepository: CourseRepository = mockk()
    private val ccoCourse = CourseEntity(1, "CCO", "des", "image.png", "P'Roof", 30, 40000)

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
    }

    private fun oddsHubHasOneCourse() {
        every { courseRepository.findAll() } returns listOf(ccoCourse)
    }

    private fun whenGetAllCourses(): List<CourseEntity> =
        CoursesController(
            CourseService(courseRepository)
        ).getAllCourses()

    private fun expectToHaveOneCourse(courses: List<CourseEntity>) {
        assert(courses.count() == 1)
        assert(courses[0] == ccoCourse)
    }

    private fun expectToNotHaveAnyCourse(courses: List<CourseEntity>) {
        assert(courses.isEmpty())
    }
}




