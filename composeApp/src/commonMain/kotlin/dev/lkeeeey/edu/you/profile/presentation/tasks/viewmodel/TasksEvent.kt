package dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel

import dev.lkeeeey.edu.you.profile.domain.models.CreateBlockTaskModel

sealed interface TasksEvent {
    data class OnEnteredSubject(val subject: String) : TasksEvent
    data class OnEnteredTitle(val title: String) : TasksEvent
    data class OnUpdateTask(val index: Int, val task: CreateBlockTaskModel) : TasksEvent
    data object OnAddTask : TasksEvent
    data object OnSave : TasksEvent
}