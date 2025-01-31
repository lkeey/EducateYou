package dev.lkeeeey.edu.you.auth.presentation.register.viewmodel

sealed interface RegisterEvent {
    data object OpenLogin : RegisterEvent
    data object OpenMain : RegisterEvent
    data object Nothing : RegisterEvent
}
