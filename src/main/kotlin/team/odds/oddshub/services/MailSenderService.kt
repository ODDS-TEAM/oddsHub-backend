package team.odds.oddshub.services

import jakarta.mail.internet.MimeMessage
import mu.KotlinLogging
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
    private val logger = KotlinLogging.logger {}

    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    fun sendBulk(emilList: List<WelcomeEmail>) {
        emilList.forEach { email -> this.send(email) }
    }

    fun send(email: WelcomeEmail) {
        val message = createWelcomeMessage(email)
        this.javaMailSender.send(message).also {
            logger.info { "Email sent welcome message to ${email.to}" }
        }
    }

    private fun createWelcomeMessage(email: WelcomeEmail): MimeMessage {
        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)
        helper.setTo(email.to)
        helper.setSubject(email.subject)
        val ctx = Context()
        ctx.setVariable("name", email.name)
        val htmlContent = templateEngine.process("welcomeMail", ctx)
        helper.setText(htmlContent, true)
        return message
    }
}