package team.odds.oddshub.services

import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import team.odds.oddshub.entities.dto.Email

@Service
class MailSenderService(
    val templateEngine: TemplateEngine
) {
    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    fun sendBulk(emilList: List<Email>) {
        emilList.forEach { email -> this.send(email) }
    }

    fun send(email: Email) {
        val message = createMessage(email)
        this.javaMailSender.send(message)
    }

    private fun createMessage(email: Email): MimeMessage {
        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)
        helper.setTo(email.to)
        helper.setSubject(email.subject)
        val ctx = Context()
        ctx.setVariable("name", email.text)
        val htmlContent = templateEngine.process("welcomeMail", ctx)
        helper.setText(htmlContent)
        return message
    }
}