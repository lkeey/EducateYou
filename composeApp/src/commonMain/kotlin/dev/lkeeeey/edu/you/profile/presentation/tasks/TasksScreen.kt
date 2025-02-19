package dev.lkeeeey.edu.you.profile.presentation.tasks

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel.TasksViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TasksScreen (
    navController: NavController,
    viewModel: TasksViewModel = koinViewModel<TasksViewModel>()
) {
    TasksView(

    )

}