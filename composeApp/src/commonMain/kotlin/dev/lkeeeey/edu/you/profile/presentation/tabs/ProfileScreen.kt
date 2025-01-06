package dev.lkeeeey.edu.you.profile.presentation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.you.app.Route
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileAction
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileView(
        state = state,
        onEvent = {
            viewModel.onEvent(event = it)
        },
        onOpenScreen = { action->
            when (action) {
                ProfileAction.OnLogOut -> {
                    navController.navigate(Route.Login)
                }
                ProfileAction.OnOpenStudents -> {
//                    TODO
//                    navController.navigate(Route.Auth)
                }
            }
        }
    )
}
