package dev.lkeeeey.edu.you.profile.presentation.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.auth.presentation.components.OutlinedText
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.profile.presentation.students.components.BackBtn
import dev.lkeeeey.edu.you.profile.presentation.students.components.ReadOnlyDropDown
import dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel.TimetableEvent
import dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel.TimetableState
import educateyou.composeapp.generated.resources.Bold
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun TimetableView (
    state: TimetableState,
    onEvent: (TimetableEvent) -> Unit,
    onOpenBack: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        BackBtn(
            text = "Расписание",
            onClick = {
                onOpenBack()
            },
            containerColor = Theme.colors.backgroundMain
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            var nameDay = ""
            when (state.weekDay) {
                0 -> nameDay = "Понедельник"
                1 -> nameDay = "Вторник"
                2 -> nameDay = "Среда"
                3 -> nameDay = "Четверг"
                4 -> nameDay = "Пятница"
                5 -> nameDay = "Суббота"
                6 -> nameDay = "Воскресенье"
            }

            IconButton(
                modifier = Modifier
                    .rotate(90f),
                enabled = state.weekDay > 0,
                onClick = {
                    onEvent(TimetableEvent.OnChangeDay(state.weekDay - 1))
                }
            ) {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    "contentDescription",
                    tint = if (state.weekDay > 0) Theme.colors.primaryBackground.copy(1f) else Theme.colors.secondaryBorder.copy(
                        1f
                    )
                )
            }

            Text(
                text = nameDay,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(Res.font.Bold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF222222),
                    letterSpacing = 0.32.sp,
                )
            )

            IconButton(
                modifier = Modifier
                    .rotate(270f),
                enabled = state.weekDay < 6,
                onClick = {
                    onEvent(TimetableEvent.OnChangeDay(state.weekDay + 1))
                }
            ) {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    "contentDescription",
                    tint = if (state.weekDay < 6) Theme.colors.primaryBackground.copy(1f) else Theme.colors.secondaryBorder.copy(
                        1f
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))



        Spacer(modifier = Modifier.height(4.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(size = 32.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Theme.colors.primaryBackground.copy(alpha = 1f),
            ),
            onClick = {
                onEvent(TimetableEvent.OnSaveLesson)
            }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                text = "Сохранить",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(600),
                    color = White,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    )
                )
        ) {

            if (state.lessons.filter { it.weekday == state.weekDay }.isNotEmpty()) {
                state.lessons.filter { it.weekday == state.weekDay }.sortedBy { it.start }.forEachIndexed { index, lesson ->
                    LessonPreview(
                        lesson = lesson
                    ) {
                        onEvent(TimetableEvent.OnDeleteLesson(lesson.id))
                    }
                }
            } else {
                Text(
                    text = "Уроков нет!",
                    modifier = Modifier
                        .fillMaxSize(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.Thin)),
                        fontWeight = FontWeight(400),
                        color = Theme.colors.secondaryBorder,
                        letterSpacing = 0.3.sp,
                    ),
                    textAlign = TextAlign.Center,

                    )
            }

            Spacer(modifier = Modifier.weight(.1f))
        }

        Spacer(modifier = Modifier.weight(.1f))
    }
}
