package com.example.domain

import com.example.domain.model.TopicsHolder
import kotlinx.coroutines.delay
import javax.inject.Inject

class SeeAllTopicsUseCase @Inject constructor() {
    suspend operator fun invoke(url: String): TopicsHolder {
        delay(1000)
        return TopicsHolder(
            items = GetFakePostsUseCase()(),
            isCategory = false,
        )
    }
}