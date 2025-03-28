package com.example.ui.components.templates

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ui.R
import com.example.ui.base.BaseUiState
import com.example.ui.theme.TextStyles
import com.example.ui.theme.color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitledScreenTemplate(
    title: String,
    onClickGoBack: () -> Unit,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.Center,
    actions: @Composable() (RowScope.() -> Unit) = {},
    baseUiState: BaseUiState = BaseUiState(),
    content: @Composable () -> Unit
) {
    ScreenTemplate(
        topBar = {
            TopAppBar(
                title = { Text(text = title,  style = TextStyles.headingExtraLarge, color = MaterialTheme.color.textPrimary) },
                navigationIcon = {
                    IconButton(onClick = onClickGoBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                },
                actions = actions,
                modifier = Modifier.focusable(),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.color.background)
            )
        },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        baseUiState = baseUiState,
    ) {
        content()
    }
}

