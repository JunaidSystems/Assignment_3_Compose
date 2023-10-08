package com.example.assignment_3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.assignment_3.R

val sanitaFontFamily = FontFamily(
    Font(R.font.sansita),
    Font(R.font.sansita_bold, FontWeight.Bold)
)

val Typography = Typography(

    bodyMedium = TextStyle(
        fontFamily = sanitaFontFamily,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = sanitaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    )
)