package dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel

sealed interface TasksAction {
    data object OpenProfileTabs : TasksAction
    data object Nothing : TasksAction
}