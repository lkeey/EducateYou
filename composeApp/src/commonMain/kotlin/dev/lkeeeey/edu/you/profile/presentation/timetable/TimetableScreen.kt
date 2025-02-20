package dev.lkeeeey.edu.you.profile.presentation.timetable

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel.TimetableViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TimetableScreen(
    viewModel: TimetableViewModel = koinViewModel<TimetableViewModel>(),
    navController: NavController
) {

}