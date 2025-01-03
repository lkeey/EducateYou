package dev.lkeeeey.edu.you.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkeeeey.edu.you.auth.login.LoginScreen
import dev.lkeeeey.edu.you.auth.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.you.auth.register.RegisterScreen
import dev.lkeeeey.edu.you.auth.register.viewmodel.RegisterViewModel
import dev.lkeeeey.edu.you.auth.splash.SplashScreen
import dev.lkeeeey.edu.you.auth.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.you.core.presentation.EduYouTheme
import dev.lkeeeey.edu.you.core.presentation.Theme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    isShowTopBar: Boolean = true,
) {
    EduYouTheme {
        val navController = rememberNavController()

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = backStackEntry?.destination

        val scaffoldState = rememberScaffoldState()

        Scaffold(
            topBar = {
                if (isShowTopBar) {
                    TopAppBar(
                        title = {  },
                        navigationIcon = {
                            Icon(
                                tint = White,
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "add",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable {
                                        navController.popBackStack()
                                    }
                            )
                        },
                        backgroundColor = Theme.colors.primaryBackground.copy(1f)
                    )
                }
            },
            bottomBar = { },
            snackbarHost = {
                SnackbarHost(it) { data ->
                    Snackbar(
                        contentColor = Theme.colors.primaryBackground.copy(.7f),
                        snackbarData = data
                    )
                }
            },
            backgroundColor = Theme.colors.backgroundMain,
            scaffoldState = scaffoldState,
        ) { innerPadding ->
            // nav host here
            NavHost(
                navController = navController,
                startDestination = Route.Auth,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            ) {

                navigation<Route.Auth>(
                    startDestination = Route.Splash
                ) {
                    composable<Route.Splash>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                    Splash screen

                        val viewModel = koinViewModel<SplashViewModel>()

                        SplashScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }

                    composable<Route.Login>(
                        enterTransition = {
                            slideInHorizontally { initialOffset ->
                                initialOffset
                            }
                        },
                        exitTransition = {
                            slideOutHorizontally { initialOffset ->
                                initialOffset
                            }
                        }
                    ) {
//                    Login Screen

                        val viewModel = koinViewModel<LoginViewModel>()

                        LoginScreen(
                            viewModel = viewModel,
                            navController = navController,
                            scaffoldState = scaffoldState
                        )
                    }

                    composable<Route.Register>(
                        enterTransition = {
                            slideInHorizontally { initialOffset ->
                                initialOffset
                            }
                        },
                        exitTransition = {
                            slideOutHorizontally { initialOffset ->
                                initialOffset
                            }
                        }
                    ) {
//                    Register Screen

                        val viewModel = koinViewModel<RegisterViewModel>()

                        RegisterScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}