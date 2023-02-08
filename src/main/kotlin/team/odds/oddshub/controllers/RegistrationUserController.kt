
package team.odds.oddshub.controllers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.entities.RegistrationUser
import team.odds.oddshub.services.RegistrationUserService
import org.springframework.web.bind.annotation.RequestBody
import team.odds.oddshub.entities.RegistrationUserPayload

@RestController
class RegistrationUserController(
    val registrationUserService: RegistrationUserService
) {

    @PostMapping("/registration")
    fun saveRegistrationUser(@RequestBody body:RegistrationUserPayload): ResponseEntity<String> {
        registrationUserService.saveRegistrationUser(body)
        return ResponseEntity("", HttpStatus.NO_CONTENT)
    }
}