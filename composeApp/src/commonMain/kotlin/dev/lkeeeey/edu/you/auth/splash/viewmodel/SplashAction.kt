package dev.lkeeeey.edu.you.auth.splash.viewmodel

sealed interface SplashAction {
    data object Nothing : SplashAction
    data object OpenMain : SplashAction
    data object OpenLogin : SplashAction
}