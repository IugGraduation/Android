package com.example.domain.model

import com.example.data.model.PostItemDto
import com.example.data.model.TopicItemDto
import com.example.domain.GetUserUseCase


data class PostItem(
    override val uuid: String = "",
    override val image: String = "",
    override val imgContentDescription: String = "",
    override val user: User = GetUserUseCase()(),
    override val title: String = "",
    override val place: String = "",
    override val details: String = "",
    override val category: String = "",
    val onClickMakeOffer: () -> Unit = {},
    var onClickGoToDetails: (() -> Unit)? = null,
    val date: String = "",
    val favoriteCategories: List<CategoryItem> = listOf(),
    val isOpen: Boolean = true,
    val rate: Float = 0f,
    val offers: List<IOffer> = listOf(),

    override val isLoading: Boolean = false,
    override val error: String? = null,


    override val imgResIdError: String? = null,
    override val imgContentDescriptionError: String? = null,
    override val titleError: String? = null,
    override val placeError: String? = null,
    override val detailsError: String? = null,
    override val categoryError: String? = null,
) : IOffer, ContentState {

    companion object {
        fun fromTopicItemDto(topicItemDto: TopicItemDto): TopicItem {
            return PostItem(
                uuid = topicItemDto.uuid.toString(),
                user = User(
                    uuid = topicItemDto.userUuid.toString(),
                    name = topicItemDto.userName.toString(),
                    image = topicItemDto.userImage.toString(),
                ),
                image = topicItemDto.postImage.toString(),
                title = topicItemDto.postName.toString(),
                isOpen = topicItemDto.status.toString() == "Active",
                details = topicItemDto.postDetails.toString(),
//                place = "",
//                category = "",
//                onClickMakeOffer = "",
//                onClickGoToDetails = "",
//                date = "",
//                favoriteCategories ="",
//                rate = "",
//                offers = "",

            )
        }

        fun fromPostItemDto(postItemDto: PostItemDto): PostItem {
            return PostItem(
                uuid = postItemDto.uuid.toString(),
                user = User(
                    uuid = postItemDto.userUuid.toString(),
                    name = postItemDto.userName.toString(),
                    image = postItemDto.userImage.toString(),
                ),
                image = postItemDto.postImage.toString(),
                title = postItemDto.postName.toString(),
                isOpen = postItemDto.status.toString() == "Active",
                details = postItemDto.postDetails.toString(),
//                place = "",
//                category = "",
//                onClickMakeOffer = "",
//                onClickGoToDetails = "",
//                date = "",
//                favoriteCategories ="",
//                rate = "",
//                offers = "",

            )
        }
    }
}


