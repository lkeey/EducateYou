package dev.lkeeeey.edu.you.auth.register.viewmodel

sealed interface RegisterEvent {
    data object OpenLogin : RegisterEvent
    data object OpenMain : RegisterEvent
    data object Nothing : RegisterEvent
}
