package com.example.domain.search

import com.example.data.repository.SearchRepository
import com.example.domain.exception.EmptyDataException
import com.example.domain.model.Chip
import com.example.domain.model.PostItem
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(
        searchValue: String, filterChipsList: List<Chip>
    ): List<PostItem> {
        val filterCategoryIds = if (filterChipsList.isEmpty()) null
        else {
            filterChipsList.filter { it.selected }
                .mapIndexed { index, chip -> "category_uuid[$index]" to chip.categoryItem.uuid }
                .toMap()
        }
        val result = searchRepository.search(searchValue, filterCategoryIds)
        val postItemList = result?.map {
            PostItem.fromTopicItemDto(it)
        }
        return postItemList ?: throw EmptyDataException()
    }
}
