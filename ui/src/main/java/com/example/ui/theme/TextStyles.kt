package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.R


val Roboto = FontFamily(
    Font(R.font.roboto_regular),
)
val SendFlowers = FontFamily(
    Font(R.font.send_flowers_regular),
)

object TextStyles {
    val headingExtraLarge: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            letterSpacing = 0.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    val headingLarge: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    val headingMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = 0.sp,
    )

    val headingSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )

    val body = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    )

    val bodyLarge: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            letterSpacing = 0.sp,
            color = MaterialTheme.colorScheme.tertiary
        )

    val hint: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            color = MaterialTheme.colorScheme.tertiary
        )

    val caption = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    )

    val captionExtraSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        letterSpacing = 0.sp
    )

    val smallCustomTitle = TextStyle(
        fontFamily = SendFlowers,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 2.sp
    )

    val largeCustomTitle = TextStyle(
        fontFamily = SendFlowers,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 2.sp
    )

}




