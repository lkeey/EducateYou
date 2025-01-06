package dev.lkeeeey.edu.you.profile.students

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.you.profile.students.viewmodel.StudentsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StudentsScreen(
    navController: NavController,
    viewModel: StudentsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    StudentsView(
        state = state,
        onEvent = viewModel.
    )
}
