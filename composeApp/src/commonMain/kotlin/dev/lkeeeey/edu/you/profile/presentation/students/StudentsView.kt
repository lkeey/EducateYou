package dev.lkeeeey.edu.you.profile.presentation.students

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.you.profile.presentation.components.ErrorMessage
import dev.lkeeeey.edu.you.profile.presentation.components.StudentCard
import dev.lkeeeey.edu.you.profile.presentation.students.components.BackBtn
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsAction
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsEvent
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsState

@Composable
fun StudentsView (
    state: StudentsState,
    onEvent: (StudentsEvent) -> Unit,
    onOpen: (StudentsAction) -> Unit
) {
    Column (
        modifier = Modifier
            .animateContentSize()
            .fillMaxSize()
            .blur(if (state.isLoading) 4.dp else 0.dp)
    ) {

        BackBtn(
            text = "Мои ученики",
            onClick = {
                onOpen(StudentsAction.OnBackScreen)
            }
        )

        if (state.errorMessage.isNotEmpty()) {
            ErrorMessage(state.errorMessage)
        }

        if (state.students.isNotEmpty()) {
            state.students.forEachIndexed { _, student ->
                StudentCard(
                    studentModel = student
                ) {
                    onEvent(StudentsEvent.OnStudentClick(username = student.name))
                }
            }
        }
    }
}

