package dev.lkeeeey.edu.you.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkeeeey.edu.you.auth.presentation.login.LoginScreen
import dev.lkeeeey.edu.you.auth.presentation.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.you.auth.presentation.register.RegisterScreen
import dev.lkeeeey.edu.you.auth.presentation.register.viewmodel.RegisterViewModel
import dev.lkeeeey.edu.you.auth.presentation.splash.SplashScreen
import dev.lkeeeey.edu.you.auth.presentation.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.you.core.presentation.EduYouTheme
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.main.MainScreen
import dev.lkeeeey.edu.you.main.viewmodel.CalendarViewModel
import dev.lkeeeey.edu.you.profile.presentation.students.StudentsScreen
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsViewModel
import dev.lkeeeey.edu.you.profile.presentation.tabs.ProfileScreen
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    EduYouTheme {
        val navController = rememberNavController()

        val scaffoldState = rememberScaffoldState()

        Scaffold(
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

                navigation<Route.Main>(
                    startDestination = Route.Calendar
                ) {
                    composable<Route.Calendar>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                        Main Screen
                        val viewModel = koinViewModel<CalendarViewModel>()

                        MainScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }

                navigation<Route.Profile>(
                    startDestination = Route.ProfileTabs
                ) {
                    composable<Route.ProfileTabs>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                        Profile Tabs Screen
                        val viewModel = koinViewModel<ProfileViewModel>()

                        ProfileScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }


                    composable<Route.Students>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                        Students Screen
                        val viewModel = koinViewModel<StudentsViewModel>()

                        StudentsScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }

                    // TODO students detail
                }
            }
        }
    }
}