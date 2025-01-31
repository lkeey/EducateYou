package dev.lkeeeey.edu.you.auth.presentation.splash.viewmodel

sealed interface SplashEvent {
    data object ClearEvents: SplashEvent
}