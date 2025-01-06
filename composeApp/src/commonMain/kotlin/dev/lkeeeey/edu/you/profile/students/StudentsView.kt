package dev.lkeeeey.edu.you.profile.students

import androidx.compose.runtime.Composable
import dev.lkeeeey.edu.you.profile.students.viewmodel.StudentsAction
import dev.lkeeeey.edu.you.profile.students.viewmodel.StudentsEvent
import dev.lkeeeey.edu.you.profile.students.viewmodel.StudentsState

@Composable
fun StudentsView (
    state: StudentsState,
    onEvent: (StudentsEvent) -> Unit,
    onOpen: (StudentsAction) -> Unit
) {

}
