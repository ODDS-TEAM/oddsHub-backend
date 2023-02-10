package team.odds.oddshub.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Called
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import team.odds.oddshub.entities.ClassEntity
import team.odds.oddshub.entities.CourseEntity
import team.odds.oddshub.entities.RegistrationUserEntity
import team.odds.oddshub.entities.dto.RegistrationUserPayload
import team.odds.oddshub.repositories.ClassRepository
import team.odds.oddshub.repositories.CourseRepository
import team.odds.oddshub.repositories.RegistrationUserRepository
import java.util.*


@SpringBootTest
@AutoConfigureMockMvc
class RegistrationUserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var registrationUserRepository: RegistrationUserRepository

    @MockkBean
    lateinit var courseRepository: CourseRepository

    @MockkBean
    lateinit var classRepository: ClassRepository

    @Nested
    inner class RegistrationUserTests {

        @Test
        fun `when user submit registration form then class full should receive class full`() {
            val classId: Long = 1234
            val registrationUserPayload = RegistrationUserPayload("MISS", "Laksana", "Ang", "laksana@kkumail.com", "0997582806",
                classId
            )
            val requestJson = jacksonObjectMapper().writeValueAsString(registrationUserPayload)
            val registrationUserEntity = RegistrationUserEntity(
                UUID.randomUUID(), "MISS", "Laksana", "Ang", "laksana@kkumail.com", "0997582806", classId
            )

            every {
                registrationUserRepository.getByClassId(classId)
            } returns listOf(registrationUserEntity)

            every {
                classRepository.getReferenceById(classId)
            } returns ClassEntity(1,1,Date(),Date())

            every {
                courseRepository.getReferenceById(1)
            } returns CourseEntity(1, "CCO", "des", "image.png", "P'Roof", 1, 40000)


            mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
            )
                .andExpect(MockMvcResultMatchers.status().isOk)

            verify {
                registrationUserRepository.save(any()) wasNot Called
            }

        }

        @Test
        fun `when user submit registration form then they should receive success response`() {
            val classId: Long = 1234
            val registrationUserPayload = RegistrationUserPayload("MISS", "Laksana", "Ang", "laksana@kkumail.com", "0997582806",
                classId
            )
            val requestJson = jacksonObjectMapper().writeValueAsString(registrationUserPayload)
            val registrationUserEntity = RegistrationUserEntity(
                UUID.randomUUID(), "MISS", "Laksana", "Ang", "laksana@kkumail.com", "0997582806", classId
            )

            every {
                registrationUserRepository.getByClassId(classId)
            } returns listOf(registrationUserEntity)

            every {
                classRepository.getReferenceById(classId)
            } returns ClassEntity(1,1,Date(),Date())

            every {
                courseRepository.getReferenceById(1)
            } returns CourseEntity(1, "CCO", "des", "image.png", "P'Roof", 30, 40000)

            every {
                registrationUserRepository.save(any())
            } returns registrationUserEntity

            mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
            )
                    .andExpect(MockMvcResultMatchers.status().isOk)

            verify {
                registrationUserRepository.save(any())
            }
        }

    }

}