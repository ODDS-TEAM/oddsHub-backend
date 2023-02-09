package team.odds.oddshub.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.entities.dto.Mail
import team.odds.oddshub.repositories.RegistrationUserRepository
import team.odds.oddshub.services.MailSenderService

@RestController
class ClassController(
    val mailSenderService: MailSenderService,
    val registrationUserRepository: RegistrationUserRepository
) {
    @PostMapping("/class/{classId}/welcome")
    fun sendEmail(@PathVariable classId: Long): ResponseEntity<String> {
        val registrationUserEntityList = registrationUserRepository.getByClassId(classId)
        if (registrationUserEntityList.isNotEmpty()) {
            val mail = Mail("newii@odds.team", "test email", "Lorem ipsum dolor sit amet [...]")
            mailSenderService.send(mail)
        }
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }
}