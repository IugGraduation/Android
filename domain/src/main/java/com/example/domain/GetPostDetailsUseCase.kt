package com.example.domain

import com.example.data.repository.PostRepository
import com.example.domain.exception.EmptyDataException
import com.example.domain.model.PostItem
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke(uuid: String): PostItem {
        return PostItem.fromPostItemDto(
            postRepository.getPostDetails(uuid) ?: throw EmptyDataException()
        )
    }
}