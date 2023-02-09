package team.odds.oddshub.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.entities.dto.Email
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
            val emailList = registrationUserEntityList.map {
                Email(it.email, "test email", "Lorem ipsum dolor sit amet [...]")
            }
            mailSenderService.sendBulk(emailList)
        }
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }
}