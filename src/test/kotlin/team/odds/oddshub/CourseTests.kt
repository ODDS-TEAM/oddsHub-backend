package team.odds.oddshub

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import team.odds.oddshub.controller.CourseController
import team.odds.oddshub.entities.Course
import team.odds.oddshub.repositories.CourseRepository
import team.odds.oddshub.services.CourseService

@SpringBootTest
class CourseTests {
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

    fun oddsHubNotHasAnyCourse() {
        every { courseRepository.findAll()} returns listOf()
    }

    fun oddsHubHasOneCourse() {
        every { courseRepository.findAll()} returns listOf(ccoCourse)
    }

    fun whenGetAllCourses(): List<Course> = CourseController(CourseService(courseRepository)).all()

    fun expectToHaveOneCourse(courses: List<Course>) {
        assert(courses.count() == 1)
        assert(courses[0] == ccoCourse)
    }

    fun expectToNotHaveAnyCourse(courses: List<Course>) {
        assert(courses.isEmpty())
    }


}