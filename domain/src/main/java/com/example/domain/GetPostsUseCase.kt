package com.example.domain

import com.example.domain.model.PostItem

class GetPostsUseCase{
    operator fun invoke(): List<PostItem> {
        val postItem = PostItem(
            image = R.drawable.img_top_interactive.toString(),
            user = GetUserUseCase()(),
            title = "10kg of Sugar Up for 10kg of Rice",
            details = "Looking for a sweet deal? I have 10 kilograms of high-quality sugar that I’d like to exchange for something useful like 10 kilograms of high-quality salt or 10 kilograms of high-quality rice or 10 kilograms of high-quality anything else that I’d like to exchange for something useful",
            category = "Category",
            onClickMakeOffer = { },
            isOpen = true,
            date = "Wed, Nov 20",
            offers = GetOffersUseCase()(),
            favoriteCategories = GetCategoriesUseCase()(),
            rate = 4.8f,
        )
        return listOf(postItem, postItem, postItem, postItem, postItem)
    }
}
