package dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel

sealed interface ProfileEvent {
    data class OnBioUpdated(val bio : String) : ProfileEvent
    data class OnSubjectUpdated(val subject : String) : ProfileEvent
    data object OnLogOut : ProfileEvent
    data object OnOpenProfile : ProfileEvent
}