package team.odds.oddshub

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MailSenderTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun whenNoOneRegisterThisCourse_thenNoOneCannotReceiveEmail() {
        val mockMailServer = MockMailServer()
        val courseWithNoParticipants = 1
        mockMvc.perform(post("/courses/$courseWithNoParticipants/welcome"))
            .andExpect(status().is2xxSuccessful)
        assert(mockMailServer.didNotSendEmail())
    }
}
