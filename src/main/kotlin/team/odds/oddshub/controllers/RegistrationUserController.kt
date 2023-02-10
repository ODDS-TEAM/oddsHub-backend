package team.odds.oddshub.controllers

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.odds.oddshub.entities.dto.RegistrationUserPayload
import team.odds.oddshub.services.RegistrationUserService


@RestController
class RegistrationUserController(
    val registrationUserService: RegistrationUserService
) {

    @PostMapping("/registration")
    fun saveRegistrationUser(@Valid @RequestBody body: RegistrationUserPayload): ResponseEntity<String> {
        val isRegistered = registrationUserService.saveRegistrationUser(body)
        if (isRegistered) {
            return ResponseEntity("OK", HttpStatus.OK)
        } 
        return ResponseEntity("FULL", HttpStatus.OK)
    }
}