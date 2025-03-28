package com.example.domain.home

import com.example.data.repository.HomeRepository
import com.example.domain.exception.EmptyDataException
import com.example.domain.model.Home
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(): Home {
        return Home.fromHomeDto(homeRepository.getHomeDto() ?: throw EmptyDataException())
    }
}
