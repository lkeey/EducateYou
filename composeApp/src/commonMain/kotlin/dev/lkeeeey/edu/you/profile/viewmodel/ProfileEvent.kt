package dev.lkeeeey.edu.you.profile.viewmodel

sealed interface ProfileEvent {
    data object OnLogOut : ProfileEvent
    data object OnOpenProfile : ProfileEvent
}