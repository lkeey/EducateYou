package dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel

sealed interface ProfileAction {
    data object OnOpenStudents : ProfileAction
    data object OnOpenCreatingTasks : ProfileAction
    data object OnLogOut : ProfileAction
    data object OnBackScreen : ProfileAction
}
