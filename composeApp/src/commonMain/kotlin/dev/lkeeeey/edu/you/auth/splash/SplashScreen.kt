package dev.lkeeeey.edu.you.auth.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.you.auth.splash.viewmodel.SplashAction
import dev.lkeeeey.edu.you.auth.splash.viewmodel.SplashEvent
import dev.lkeeeey.edu.you.auth.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.you.app.Route
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen (
    viewModel: SplashViewModel = koinViewModel(),
    navController: NavController
) {

    SplashView()

    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.action) {
        SplashAction.Nothing -> { }
        SplashAction.OpenLogin -> {
            navController.navigate(Route.Login)

            viewModel.onEvent(SplashEvent.ClearEvents)
        }
        SplashAction.OpenMain -> {
//            TODO
//            navController.navigate(Route.Main)

            viewModel.onEvent(SplashEvent.ClearEvents)
        }
    }



}