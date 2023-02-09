package team.odds.oddshub.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.entities.dto.WelcomeEmail
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
        val subject = "Welcome to Certified LeSS Practitioner: Principles to Practices, Bangkok 28-30 November 2023"
        if (registrationUserEntityList.isNotEmpty()) {
            val welcomeEmailList = registrationUserEntityList.map {
                WelcomeEmail(it.email, subject, "${it.firstName} ${it.lastName}")
            }
            mailSenderService.sendBulk(welcomeEmailList)
        }
        return ResponseEntity("Send e-mail successfully!", HttpStatus.OK)
    }
}