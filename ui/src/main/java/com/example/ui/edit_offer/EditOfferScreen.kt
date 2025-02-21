package com.example.ui.edit_offer

import android.net.Uri
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.GetFakeOfferDetailsUseCase
import com.example.ui.R
import com.example.ui.base.MyUiState
import com.example.ui.components.atoms.SwapWiseFilledButton
import com.example.ui.components.atoms.SwapWiseOutlineButton
import com.example.ui.components.atoms.SwapWiseTextField
import com.example.ui.components.atoms.VerticalSpacer
import com.example.ui.components.molecules.ProductImage
import com.example.ui.components.molecules.TitledChipsList
import com.example.ui.components.templates.TitledScreenTemplate
import com.example.ui.models.OfferItemUiState
import com.example.ui.theme.GraduationProjectTheme
import com.example.ui.theme.Spacing16
import com.example.ui.theme.Spacing24
import com.example.ui.theme.Spacing8
import com.example.ui.theme.TextStyles
import com.example.ui.theme.color

@Composable
fun EditOfferScreen(navController: NavController, viewModel: EditOfferViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.baseUiState.shouldNavigateUp) {
        if (state.baseUiState.shouldNavigateUp) navController.navigateUp()
    }

    EditOfferContent(
        state = state,
        editInteractions = viewModel,
        onClickGoBack = navController::navigateUp,
    )
}

@Composable
fun EditOfferContent(
    state: MyUiState<OfferItemUiState>,
    editInteractions: IEditOfferInteractions,
    onClickGoBack: () -> Unit
) {
    TitledScreenTemplate(
        title = stringResource(R.string.edit_offer),
        onClickGoBack = onClickGoBack,
        baseUiState = state.baseUiState,
    ) {
        ProductImage(
            state.data.offerItem.imageLink,
            onImagePicked = editInteractions::onSelectedImageChange
        )
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(scrollState, orientation = Orientation.Vertical)
                .padding(Spacing16),
            verticalArrangement = Arrangement.spacedBy(Spacing8)
        ) {
            Text(
                stringResource(R.string.offer_info),
                style = TextStyles.headingLarge,
                color = MaterialTheme.color.textPrimary
            )
            SwapWiseTextField(
                value = state.data.offerItem.title,
                onValueChange = editInteractions::onTitleChange,
                placeholder = stringResource(R.string.offer_title),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_title),
                        contentDescription = stringResource(R.string.offer_title),
                        tint = MaterialTheme.color.textTertiary
                    )
                },
                errorMessage = state.data.offerError.titleError,
            )
            SwapWiseTextField(
                value = state.data.offerItem.place,
                onValueChange = editInteractions::onPlaceChange,
                placeholder = stringResource(R.string.your_place),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = stringResource(R.string.your_place),
                        tint = MaterialTheme.color.textTertiary
                    )
                },
                errorMessage = state.data.offerError.placeError,
            )
            SwapWiseTextField(
                value = state.data.offerItem.details,
                onValueChange = editInteractions::onDetailsChange,
                placeholder = stringResource(R.string.details),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_details),
                        contentDescription = stringResource(R.string.details),
                        tint = MaterialTheme.color.textTertiary
                    )
                },
                isMultiline = true,
                errorMessage = state.data.offerError.detailsError,
            )
            VerticalSpacer(Spacing16)
            TitledChipsList(
                title = stringResource(R.string.category_of_the_offer),
                textStyle = TextStyles.headingLarge,
                chipsList = state.data.chipsList.onEach {
                    it.selected = it.text == state.data.offerItem.category
                },
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Spacing16, bottom = Spacing24),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            SwapWiseFilledButton(
                onClick = editInteractions::onClickSave,
                text = stringResource(R.string.save),
                modifier = Modifier.padding(horizontal = Spacing16)
            )
            VerticalSpacer(Spacing8)
            SwapWiseOutlineButton(
                onClick = editInteractions::onClickDelete,
                text = stringResource(R.string.delete),
                modifier = Modifier.padding(horizontal = Spacing16)
            )
        }

    }
}


@Preview(showBackground = true, device = "spec:width=1080px,height=2540px,dpi=440")
@Composable
fun PreviewPostDetailsContent() {
    GraduationProjectTheme {
        EditOfferContent(
            state = MyUiState(
                OfferItemUiState(
                offerItem = GetFakeOfferDetailsUseCase()().copy(
                    category = "Food and beverages0",
                )
                )
            ),
            editInteractions = object : IEditOfferInteractions {
                override fun onTitleChange(title: String) {}
                override fun onPlaceChange(place: String) {}
                override fun onDetailsChange(details: String) {}
                override fun onSelectedImageChange(selectedImageUri: Uri) {}
                override fun onClickSave() {}
                override fun onClickDelete() {}
            },
            onClickGoBack = { }
        )
    }
}