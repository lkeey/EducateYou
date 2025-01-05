package dev.lkeeeey.edu.you.auth.presentation.login.viewmodel

sealed interface LoginEvent {
    data object OpenSignUp : LoginEvent
    data object OpenMain : LoginEvent
    data object Nothing : LoginEvent
}