package dev.lkeeeey.edu.you.profile.presentation.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.main.domain.LessonModel
import dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel.TimetableEvent
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun LessonPreview (
    lesson: LessonModel,
    onDelete: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = lesson.user.name,
            modifier = Modifier
                .fillMaxSize(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Theme.colors.secondaryBorder,
                letterSpacing = 0.3.sp,
            ),
        )

        IconButton(
            modifier = Modifier
                .rotate(90f),
            onClick = {
                onDelete()
            }
        ) {
            Icon(
                Icons.Filled.Delete,
                "contentDescription",
                tint = Theme.colors.errorColor
            )
        }
    }
}
