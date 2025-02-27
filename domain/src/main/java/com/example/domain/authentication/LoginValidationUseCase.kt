package com.example.domain.authentication

import com.example.domain.exception.InvalidPasswordException
import com.example.domain.exception.InvalidPhoneException
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginValidationUseCase @Inject constructor() {
    suspend operator fun invoke(
        phone: String,
        password: String,
    ) {
        validatePhone(phone)
        validatePassword(password)
        delay(500)
        //todo: login
    }
}

fun validatePhone(input: String) {
    if (input.length < 10) throw InvalidPhoneException()
}

fun validatePassword(input: String) {
    if (input.length < 8) throw InvalidPasswordException()
}
