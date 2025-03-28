package com.example.ui.components.templates

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.domain.model.User
import com.example.ui.R
import com.example.ui.base.BaseUiState
import com.example.ui.components.atoms.HorizontalSpacer
import com.example.ui.components.atoms.VerticalSpacer
import com.example.ui.models.BottomBarUiState
import com.example.ui.theme.Spacing16
import com.example.ui.theme.Spacing24
import com.example.ui.theme.Spacing4
import com.example.ui.theme.Spacing8
import com.example.ui.theme.TextStyles

@Composable
fun HomeTemplate(
    user: User,
    bottomBarState: BottomBarUiState,
    floatingActionButton: @Composable () -> Unit = {},
    baseUiState: BaseUiState = BaseUiState(),
    content: @Composable () -> Unit
) {
    BottomBarTemplate(
        topBar = {
            HomeTopBar(
                title = "Good Morning \uD83D\uDC4B",
                subtitle = user.name,
                imagePainter = rememberAsyncImagePainter(user.imageLink),
                modifier = Modifier.focusable()
            )
        },
        bottomBarState = bottomBarState,
        floatingActionButton = floatingActionButton,
        baseUiState = baseUiState,
    ) {
        content()
    }
}

@Composable
fun HomeTopBar(
    title: String,
    subtitle: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing16),
        contentAlignment = Alignment.CenterStart
    ) {
        Row {
            Image(
                painter = imagePainter,
                contentDescription = stringResource(R.string.user_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(40.dp)
            )
            HorizontalSpacer(Spacing8)
            Column (modifier = Modifier.padding(vertical = Spacing8)) {
                Text(
                    text = title,
                    style = TextStyles.captionLarge,
                )
                VerticalSpacer(Spacing4)
                Text(
                    text = subtitle,
                    style = TextStyles.headingSmall,
                )
            }
            VerticalSpacer(Spacing24)
        }
    }
}