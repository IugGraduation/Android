package com.example.ui.models

import com.example.domain.model.OfferItem
import com.example.ui.base.BaseUiState

data class OfferItemUiState(
    val offerItem: OfferItem = OfferItem(),
    val chipsList: List<Chip> = listOf(),

    val offerError: PostErrorUiState = PostErrorUiState(),
    val baseUiState: BaseUiState = BaseUiState(),
)
