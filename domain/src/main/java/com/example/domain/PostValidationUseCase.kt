package com.example.domain

import com.example.domain.exception.InvalidDetailsException
import com.example.domain.exception.InvalidPlaceException
import com.example.domain.exception.InvalidTitleException
import javax.inject.Inject

class PostValidationUseCase @Inject constructor() {
    operator fun invoke(
        title: String,
        place: String,
        details: String,
    ) {
        validateTitle(title)
        validatePlace(place)
        validateDetails(details)
    }
}

private fun validateTitle(input: String) {
    if (input.length < 3) throw InvalidTitleException()
}

private fun validatePlace(input: String) {
    if (input.length < 3) throw InvalidPlaceException()
}

private fun validateDetails(input: String) {
    if (input.length < 3) throw InvalidDetailsException()
}