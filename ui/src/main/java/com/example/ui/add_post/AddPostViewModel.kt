package com.example.ui.add_post

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import com.example.domain.AddPostUseCase
import com.example.domain.GetCategoriesNamesUseCase
import com.example.domain.exception.InvalidDetailsException
import com.example.domain.exception.InvalidPlaceException
import com.example.domain.exception.InvalidTitleException
import com.example.domain.model.PostItem
import com.example.ui.base.BaseViewModel
import com.example.ui.base.StringsResource
import com.example.ui.models.Chip
import com.example.ui.models.PostErrorUiState
import com.example.ui.models.PostItemUiState
import com.example.ui.util.empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val stringsResource: StringsResource,
    private val getCategoriesNamesUseCase: GetCategoriesNamesUseCase,
    private val addPostUseCase: AddPostUseCase,
) : BaseViewModel<PostItemUiState>(PostItemUiState()), IAddPostInteractions {
    private val args = AddPostArgs(savedStateHandle)

    init {
        prepareChipsList()
        updatePostItem { copy(title = args.postTitle) }
    }


    private fun prepareChipsList() {
        tryToExecute(
            call = { getCategoriesNamesUseCase() },
            onSuccess = ::onGetChipsDataSuccess,
        )
    }

    private fun onGetChipsDataSuccess(categoriesNames: List<String>) {
        val chipsList = List(categoriesNames.size) { index ->
            Chip(text = categoriesNames[index], selected = false, onClick = ::onCategoryChange)
        }
        val favoriteChipsList = chipsList.map { it.copy() }.onEach {
            it.onClick = ::onFavoriteCategoryChange
        }
        _state.update {
            it.copy(chipsList = chipsList, favoriteChipsList = favoriteChipsList)
        }
    }


    private fun updatePostItem(update: PostItem.() -> PostItem) {
        _state.update {
            it.copy(postItem = it.postItem.update())
        }
    }

    private fun updateFieldError(
        titleError: String = String.empty(),
        placeError: String = String.empty(),
        detailsError: String = String.empty(),
    ) {
        _state.update {
            it.copy(
                postError = PostErrorUiState(
                    titleError = titleError,
                    placeError = placeError,
                    detailsError = detailsError,
                )
            )
        }
    }

    override fun onTitleChange(title: String) {
        updateFieldError()
        updatePostItem { copy(title = title) }
    }

    override fun onDetailsChange(details: String) {
        updateFieldError()
        updatePostItem { copy(details = details) }
    }

    override fun onPlaceChange(place: String) {
        updateFieldError()
        updatePostItem { copy(place = place) }
    }

    override fun onSelectedImageChange(selectedImageUri: Uri) {
        updatePostItem { copy(imageLink = selectedImageUri.toString()) }
    }

    fun onCategoryChange(category: String) {
        updateFieldError()
        updatePostItem { copy(category = category) }
    }

    fun onFavoriteCategoryChange(category: String) {
        val newFavoriteChipList = if (state.value.postItem.favoriteCategories.contains(category)) {
            _state.value.postItem.favoriteCategories - category
        } else {
            _state.value.postItem.favoriteCategories + category
        }

        updatePostItem { copy(favoriteCategories = newFavoriteChipList.toMutableList()) }
    }


    override fun onClickAdd() {
        tryToExecute(
            call = { addPostUseCase(state.value.postItem) },
            onSuccess = { navigateUp() },
            onError = ::onAddPostFail
        )
    }


    private fun onAddPostFail(throwable: Throwable) {
        when (throwable) {
            is InvalidTitleException -> {
                updateFieldError(titleError = stringsResource.invalidTitle)
            }

            is InvalidPlaceException -> {
                updateFieldError(placeError = stringsResource.invalidPlace)
            }

            is InvalidDetailsException -> {
                updateFieldError(detailsError = stringsResource.invalidDetails)
            }

            else -> onActionFail(throwable)
        }
    }

}