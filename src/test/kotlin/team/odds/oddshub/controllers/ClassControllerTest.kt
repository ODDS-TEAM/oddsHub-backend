package team.odds.oddshub.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import team.odds.oddshub.entities.RegistrationUserEntity
import team.odds.oddshub.entities.dto.Mail
import team.odds.oddshub.repositories.RegistrationUserRepository
import team.odds.oddshub.services.MailSenderService
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class ClassControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var javaMailSenderService: MailSenderService

    @MockkBean
    lateinit var registrationUserRepository: RegistrationUserRepository

    @Nested
    inner class SendMailTest {

        @BeforeEach
        fun beforeEach() {
            every { javaMailSenderService.send(any()) } just runs
        }

        @Test
        fun `when no one register this class then no one cannot receive email`() {
            val classWithNoParticipants = 0

            givenParticipantOfClass(0, emptyList())
            mockMvc.perform(MockMvcRequestBuilders.post("/class/$classWithNoParticipants/welcome"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)


            verify { javaMailSenderService wasNot Called }
        }

        @Test
        fun `when have one participant in this class then participant should receive email`() {
            val classWithOneParticipant = 1L
            givenParticipantOfClass(classWithOneParticipant, listOf("newii@odds.team"))
            mockMvc.perform(MockMvcRequestBuilders.post("/class/$classWithOneParticipant/welcome"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)

            verify {
                javaMailSenderService.send(Mail("newii@odds.team", "test email", "Lorem ipsum dolor sit amet [...]"))
            }
        }

        private fun givenParticipantOfClass(classId: Long, participantList: List<String>) {
            val participantListEntity = participantList.map { email ->
                RegistrationUserEntity(
                    UUID.randomUUID(),
                    "Miss",
                    "Newii",
                    "sad boy",
                    email,
                    "09090909090",
                    classId
                )
            }

            every { registrationUserRepository.getByClassId(classId) } returns participantListEntity
        }
    }
}