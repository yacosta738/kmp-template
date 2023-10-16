package io.kmptemplate.ui.base.design

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp

internal val AppTypography: Typography
    @Composable get() {
        val fontFamily = FontFamily(
            font("barlow_light", FontWeight.Light, FontStyle.Normal),
            font("barlow_normal", FontWeight.Normal, FontStyle.Normal),
            font("barlow_medium", FontWeight.Medium, FontStyle.Normal),
            font("barlow_bold", FontWeight.Bold, FontStyle.Normal),
        )
        return Typography(
            bodyLarge = TextStyle(
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
                baselineShift = BaselineShift.Subscript
            ),
            bodyMedium = TextStyle(
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
                baselineShift = BaselineShift.Subscript
            ),
        )
    }

@Composable
internal expect fun font(font: String, weight: FontWeight, style: FontStyle): Font
