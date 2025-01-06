package dev.lkeeeey.edu.you.profile.tabs.viewmodel

sealed interface ProfileAction {
    data object OnOpenStudents : ProfileAction
    data object OnLogOut : ProfileAction
}
