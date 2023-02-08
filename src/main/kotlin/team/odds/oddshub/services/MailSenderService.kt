package team.odds.oddshub.services

import jakarta.mail.MessagingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class MailSenderService {
    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    fun send(to: String, subject: String, text: String) {
        val mail = javaMailSender.createMimeMessage()
        try {
            val helper = MimeMessageHelper(mail)
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(text)
        } catch (e: MessagingException) {
            e.printStackTrace()
        } finally {
            javaMailSender.send(mail)
        }
    }
}