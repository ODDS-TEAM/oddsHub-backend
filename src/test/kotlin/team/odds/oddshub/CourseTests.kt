package team.odds.oddshub

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import team.odds.oddshub.controller.CourseController
import team.odds.oddshub.model.Course

@SpringBootTest
class CourseTests {

    @Test
    fun onlyOneCourseAvailableForUser() {
        expectToHaveOneCourse(whenGetAllCourse())
    }

    fun whenGetAllCourse(): List<Course> = CourseController().all()

    fun expectToHaveOneCourse(courses: List<Course>) {
        assert(courses.count() == 1)
        assert(courses[0].name == "CCO")
    }


}