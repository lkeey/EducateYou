package dev.lkeeeey.edu.you.main.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.Font

/*
* show current month
* */

@Composable
fun MonthText(
    selectedMonth: Month
) {
    Text(
        text = selectedMonth.name,
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(Res.font.Thin)),
            fontWeight = FontWeight(500),
            color = Color(0xFF222222),
            letterSpacing = 0.32.sp,
        )
    )
}
