@file:OptIn(ExperimentalUnitApi::class, ExperimentalUnitApi::class)

package com.edts.sampleapps.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val TopBarTextStyleHeaderNormal = TextStyle(
    color = Color.White,
    fontSize = TextUnit(14f, TextUnitType.Sp)
)

val TopBarTextStyleHeaderBold = TextStyle(
    color = Color.White,
    fontSize = TextUnit(14f, TextUnitType.Sp),
    fontWeight = FontWeight.Bold
)

val TextStyleBold = TextStyle(fontWeight = FontWeight.Bold)
val TextStyleSemiBold = TextStyle(fontWeight = FontWeight.SemiBold)
val TextStyleOrange = TextStyle(color = Orange)

val TextSizeTopBar = TextUnit(16f, TextUnitType.Sp)
val TextSize14 = TextUnit(14f, TextUnitType.Sp)
val TextSize12 = TextUnit(12f, TextUnitType.Sp)
val TextSize10 = TextUnit(10f, TextUnitType.Sp)