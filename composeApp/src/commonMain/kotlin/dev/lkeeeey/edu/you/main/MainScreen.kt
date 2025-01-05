package dev.lkeeeey.edu.you.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.you.main.viewmodel.CalendarAction
import dev.lkeeeey.edu.you.main.viewmodel.CalendarViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen (
    viewModel: CalendarViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CalendarView(
        state = state,
        onEvent = { event->
//            viewModel.onEvent(event)
        },
        onOpen = {

        }
    )
}
