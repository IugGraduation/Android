package com.example.domain

import com.example.data.repository.OfferRepository
import com.example.domain.model.OfferItem
import javax.inject.Inject


class AddOfferUseCase @Inject constructor(private val offerRepository: OfferRepository) {
    suspend operator fun invoke(postId: String, offerItem: OfferItem): Boolean =
        offerRepository.addOffer(postId, offerItem.toOfferItemDto())
}
