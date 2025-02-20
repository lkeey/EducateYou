package dev.lkeeeey.edu.you.main.viewmodel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.main.domain.LessonModel
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun ScheduledLesson (
    lessonModel: LessonModel
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(
                color = White,
                shape = RoundedCornerShape(
                    size = 8.dp
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = "${lessonModel.start} - ${lessonModel.end}",
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Theme.colors.blackProfile,
                letterSpacing = 0.3.sp,
            )
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "${lessonModel.user.name} - ${lessonModel.user.username}",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Theme.colors.blackProfile,
                letterSpacing = 0.3.sp,
            )
        )
    }
}
