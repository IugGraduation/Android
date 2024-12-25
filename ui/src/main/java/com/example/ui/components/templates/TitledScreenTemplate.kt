package com.example.ui.components.templates

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.domain.model.ContentState
import com.example.ui.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitledScreenTemplate(
    title: String,
    onClickGoBack: () -> Unit,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.Center,
    contentState: ContentState = object : ContentState {
        override val isLoading: Boolean = false
        override val error: String? = null
    },
    content: @Composable () -> Unit
) {
    ScreenTemplate(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = onClickGoBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                }
            )
        },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        contentState = contentState,
    ) {
        content()
    }
}

