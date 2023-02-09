package team.odds.oddshub.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
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
import team.odds.oddshub.entities.RegistrationUserEntity
import team.odds.oddshub.entities.dto.RegistrationUserPayload
import team.odds.oddshub.repositories.RegistrationUserRepository
import java.util.*


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
            val registrationUserEntity = RegistrationUserEntity(
                UUID.randomUUID(), "MISS", "Laksana", "Ang", "laksana@kkumail.com", "0997582806", 1234
            )

            every {
                registrationUserRepository.save(any())
            } returns registrationUserEntity

            mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
            )
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)

            verify {
                registrationUserRepository.save(any())
            }
        }

    }

}