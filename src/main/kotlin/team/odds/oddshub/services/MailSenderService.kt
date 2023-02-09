package team.odds.oddshub.services

import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import team.odds.oddshub.entities.dto.WelcomeEmail

@Service
class MailSenderService(
    val templateEngine: TemplateEngine
) {
    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    fun sendBulk(emilList: List<WelcomeEmail>) {
        emilList.forEach { email -> this.send(email) }
    }

    fun send(email: WelcomeEmail) {
        val message = createWelcomeMessage(email)
        this.javaMailSender.send(message)
    }

    private fun createWelcomeMessage(email: WelcomeEmail): MimeMessage {
        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)
        helper.setTo(email.to)
        helper.setSubject(email.subject)
        val ctx = Context()
        ctx.setVariable("name", email.name)
        val htmlContent = templateEngine.process("welcomeMail", ctx)
        helper.setText(htmlContent)
        return message
    }
}