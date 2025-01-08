package dev.lkeeeey.edu.you.profile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.core.presentation.Theme
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun ErrorMessage (
    text: String
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Theme.colors.errorColor,
                textAlign = TextAlign.Center
            ),
        )
    }

}
