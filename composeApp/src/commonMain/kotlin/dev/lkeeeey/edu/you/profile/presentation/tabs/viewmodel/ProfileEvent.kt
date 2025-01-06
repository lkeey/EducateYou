package dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel

sealed interface ProfileEvent {
    data object OnLogOut : ProfileEvent
    data object OnOpenProfile : ProfileEvent
}