package dev.lkeeeey.edu.you.profile.presentation.students

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsAction
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsEvent
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StudentsScreen(
    navController: NavController,
    viewModel: StudentsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    StudentsView(
        state = state,
        onEvent = {
            viewModel.onEvent(it)
        },
        onOpen = {
            when (it) {
                StudentsAction.OnOpenStudentDetails -> {

                }

                StudentsAction.OnBackScreen -> {
                    navController.popBackStack()
                }
            }
        }
    )

    LaunchedEffect(true) {
        viewModel.onEvent(StudentsEvent.OnLoadStudents)
    }
}
