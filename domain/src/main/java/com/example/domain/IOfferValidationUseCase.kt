package com.example.domain

import com.example.domain.model.IOffer
import com.example.domain.resource.ResourceProvider
import javax.inject.Inject

class IOfferValidationUseCase @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val validateTitleUseCase: ValidateAtLeast3CharacterUseCase,
    private val validatePlaceUseCase: ValidateAtLeast3CharacterUseCase,
    private val validateDetailsUseCase: ValidateAtLeast3CharacterUseCase,
) {

    operator fun invoke(offer: IOffer): IOffer {
        val titleResult = validateTitleUseCase(
            offer.title, resourceProvider.getString(R.string.title)
        )
        val placeResult = validatePlaceUseCase(
            offer.place, resourceProvider.getString(R.string.place)
        )
        val detailsResult = validateDetailsUseCase(
            offer.details, resourceProvider.getString(R.string.details)
        )

        val updatedOffer = offer.customCopy(
            titleError = titleResult.exceptionOrNull()?.message,
            placeError = placeResult.exceptionOrNull()?.message,
            detailsError = detailsResult.exceptionOrNull()?.message,
        )
        return updatedOffer
    }
}
