package com.example.ui.edit_post

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import com.example.domain.DeletePostUseCase
import com.example.domain.EditPostUseCase
import com.example.domain.GetCategoriesNamesUseCase
import com.example.domain.GetPostDetailsUseCase
import com.example.domain.exception.InvalidDetailsException
import com.example.domain.exception.InvalidPlaceException
import com.example.domain.exception.InvalidTitleException
import com.example.domain.model.PostItem
import com.example.ui.base.BaseViewModel
import com.example.ui.base.MyUiState
import com.example.ui.base.StringsResource
import com.example.ui.models.ChipUiState
import com.example.ui.models.PostErrorUiState
import com.example.ui.models.PostItemUiState
import com.example.ui.util.empty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditPostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val stringsResource: StringsResource,
    private val getPostDetailsUseCase: GetPostDetailsUseCase,
    private val getCategoriesNamesUseCase: GetCategoriesNamesUseCase,
    private val editPostUseCase: EditPostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
) : BaseViewModel<PostItemUiState>(PostItemUiState()), IEditPostInteractions {
    private val args = EditPostArgs(savedStateHandle)

    init {
        getPostDetails()
    }

    private fun getPostDetails() {
        tryToExecute(
            call = { getPostDetailsUseCase(args.postId) },
            onSuccess = ::onGetPostDetailsSuccess,
        )
    }

    private fun onGetPostDetailsSuccess(data: PostItem) {
        _state.value = MyUiState(PostItemUiState(postItem = data))
        prepareChipsList()
    }


    private fun prepareChipsList() {
        tryToExecute(
            call = { getCategoriesNamesUseCase() },
            onSuccess = ::onGetChipsDataSuccess,
        )
    }

    private fun onGetChipsDataSuccess(categoriesNames: List<String>) {
        val chipsList = List(categoriesNames.size) { index ->
            ChipUiState(
                text = categoriesNames[index], selected = false, onClick = ::onCategoryChange
            )
        }
        val favoriteChipsList = chipsList.map { it.copy() }.onEach {
            it.onClick = ::onFavoriteCategoryChange
        }
        updateData {
            copy(chipsList = chipsList, favoriteChipsList = favoriteChipsList)
        }
    }


    private fun updateFieldError(
        titleError: String = String.empty(),
        placeError: String = String.empty(),
        detailsError: String = String.empty(),
    ) {
        updateData {
            copy(
                postError = PostErrorUiState(
                    titleError = titleError,
                    placeError = placeError,
                    detailsError = detailsError,
                )
            )
        }
    }

    private fun updatePostItem(update: PostItem.() -> PostItem) {
        updateData {
            copy(postItem = postItem.update())
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

    override fun onIsOpenChange(isOpen: Boolean) {
        updatePostItem { copy(isOpen = isOpen) }
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
        val newFavoriteChipList =
            if (state.value.data.postItem.favoriteCategories.contains(category)) {
                _state.value.data.postItem.favoriteCategories - category
        } else {
                _state.value.data.postItem.favoriteCategories + category
        }

        updatePostItem { copy(favoriteCategories = newFavoriteChipList.toMutableList()) }
    }


    override fun onClickSave() {
        tryToExecute(
            call = { editPostUseCase(state.value.data.postItem) },
            onSuccess = { navigateUp() },
            onError = ::onSavePostFail
        )
    }

    private fun onSavePostFail(throwable: Throwable) {
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


    override fun onClickDelete() {
        tryToExecute(
            call = { deletePostUseCase(state.value.data.postItem.uuid) },
            onSuccess = { navigateUp() },
        )
    }


}