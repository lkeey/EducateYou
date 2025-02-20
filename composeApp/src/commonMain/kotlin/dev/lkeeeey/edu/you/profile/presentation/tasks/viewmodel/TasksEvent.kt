package dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel

sealed interface TasksEvent {
    data class OnEnteredSubject(val subject: String) : TasksEvent
    data class OnEnteredTitle(val title: String) : TasksEvent
    data class OnUpdateTaskContent(val index: Int, val content: String) : TasksEvent
    data class OnUpdateTaskAnswer(val index: Int, val answer: String) : TasksEvent
    data object OnClearActions : TasksEvent
    data object OnAddTask : TasksEvent
    data object OnSave : TasksEvent
}