package team.odds.oddshub.services

import jakarta.mail.MessagingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import team.odds.oddshub.entities.dto.Mail

@Service
class MailSenderService {
    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    fun send(mail: Mail) {
        val javaMail = this.javaMailSender.createMimeMessage()
        try {
            val helper = MimeMessageHelper(javaMail)
            helper.setTo(mail.to)
            helper.setSubject(mail.subject)
            helper.setText(mail.text)
        } catch (e: MessagingException) {
            e.printStackTrace()
        } finally {
            this.javaMailSender.send(javaMail)
        }

    }
}

