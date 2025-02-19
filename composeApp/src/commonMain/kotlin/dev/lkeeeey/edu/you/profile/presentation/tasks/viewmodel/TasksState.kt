package dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel

import dev.lkeeeey.edu.you.profile.domain.models.CreateBlockModel

data class TasksState (
    val enteredBlock: CreateBlockModel = CreateBlockModel(),
    val isLoading : Boolean = false,
    val error: String = ""
)