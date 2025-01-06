package dev.lkeeeey.edu.you.profile.presentation.students.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.core.presentation.Theme
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import educateyou.composeapp.generated.resources.ic_btn_back
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun BackBtn(
    text: String,
    onClick: () -> Unit
) {
    Spacer(Modifier.height(36.dp))

    Row(
        modifier = Modifier
           .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier
            .width(8.dp))
        
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onClick()
                },
            tint = Theme.colors.blackProfile,
            painter = painterResource(Res.drawable.ic_btn_back),
            contentDescription = "back",
        )

        Text(
            text = text,
            modifier = Modifier
                .weight(1f),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(600),
                color = Theme.colors.blackProfile,
                textAlign = TextAlign.Center
            ),
            textAlign = TextAlign.Center
        )
    }

    Spacer(Modifier.height(16.dp))
}
