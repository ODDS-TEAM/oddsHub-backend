package team.odds.oddshub.services

import jakarta.mail.MessagingException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class MailSenderService(
    val templateEngine: TemplateEngine,
    val javaMailSender: JavaMailSender
) {

    fun send(to: String, subject: String, text: String) {
        val mail = javaMailSender.createMimeMessage()
        try {
            val helper = MimeMessageHelper(mail)
            helper.setTo(to)
            helper.setSubject(subject)
            val ctx = Context()
            ctx.setVariable("someVariable", "test")
            val htmlContent = templateEngine.process("welcomeMail", ctx)
            helper.setText(htmlContent, true)
        } catch (e: MessagingException) {
            e.printStackTrace()
        } finally {
            javaMailSender.send(mail)
        }
    }
}