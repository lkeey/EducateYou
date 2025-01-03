package dev.lkeeeey.edu.you.auth.splash.viewmodel

sealed interface SplashEvent {
    data object ClearEvents: SplashEvent
}