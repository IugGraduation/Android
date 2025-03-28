package com.example.ui.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.ui.R
import com.example.ui.theme.ProfileToggleHeight
import com.example.ui.theme.Spacing4
import com.example.ui.theme.Spacing8
import com.example.ui.theme.TextStyles
import com.example.ui.theme.WhitePrimary
import com.example.ui.theme.ZeroDp
import com.example.ui.theme.color
import kotlinx.coroutines.launch

@Composable
fun ProfileToggle(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val tabs = listOf(Tab.Information, Tab.Posts, Tab.Settings)
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(ProfileToggleHeight)
            .clip(RoundedCornerShape(ProfileToggleHeight / 2))
            .background(color = MaterialTheme.color.onBackground)
            .padding(Spacing4),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEachIndexed { index, tab ->
            TabButton(
                modifier = Modifier.weight(1f),
                tab = tab,
                isSelected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page = tabs.indexOf(tab))
                    }

                }
            )
        }
    }
}

@Composable
private fun TabButton(
    modifier: Modifier = Modifier,
    tab: Tab, isSelected: Boolean,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier
            .height(ProfileToggleHeight - Spacing8)
            .background(
                color = if (isSelected) MaterialTheme.color.primary else MaterialTheme.color.transparent,
                shape = CircleShape
            ),
        contentPadding = PaddingValues(ZeroDp),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = tab.titleRes),
            style = TextStyles.headingSmall,
            color = if (isSelected) WhitePrimary else MaterialTheme.color.textSecondary        )
    }
}

private enum class Tab(val titleRes: Int) {
    Information(R.string.information),
    Posts(R.string.posts),
    Settings(R.string.settings)
}