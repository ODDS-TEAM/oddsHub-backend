package team.odds.oddshub.controllers

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import team.odds.oddshub.repositories.RegistrationUserRepository
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Test
import com.ninjasquad.springmockk.MockkBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import team.odds.oddshub.services.RegistrationUserService
import team.odds.oddshub.entities.RegistrationUserPayload
import org.springframework.http.MediaType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.*
import team.odds.oddshub.entities.RegistrationUser


@SpringBootTest
@AutoConfigureMockMvc
class RegistrationUserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var registrationUserRepository: RegistrationUserRepository

    @Nested
    inner class RegistrationUserTests {


        @Test
        fun `when user submit registration form then they should receive success response`() {
            val registrationUserPayload = RegistrationUserPayload("MISS", "Laksana", "Ang", "laksana@kkumail.com", "0997582806", 1234)
            val requestJson = jacksonObjectMapper().writeValueAsString(registrationUserPayload)
            val registrationUser = RegistrationUser(
                    0, "MISS", "Laksana", "Ang", "laksana@kkumail.com", "0997582806", 1234
            )

            every {
                registrationUserRepository.save(any())
            } returns RegistrationUser()


            mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
            )
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)

            verify {
                registrationUserRepository.save(registrationUser)
            }
        }

    }

}