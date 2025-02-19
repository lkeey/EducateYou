package dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.you.auth.domain.AuthRepository
import dev.lkeeeey.edu.you.profile.domain.ProfileRepository
import dev.lkeeeey.edu.you.profile.domain.models.CreateBlockTaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TasksViewModel (
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TasksState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    fun onEvent(
        event: TasksEvent
    ) {
        when (event) {
            TasksEvent.OnAddTask -> {
                val tasks = state.value.enteredBlock.tasks.toMutableList()
                tasks.add(CreateBlockTaskModel())

                val block = state.value.enteredBlock.copy(
                    tasks = tasks
                )

                _state.update {
                    it.copy(
                        enteredBlock = block
                    )
                }
            }
            is TasksEvent.OnEnteredSubject -> {
                _state.update {
                    it.copy(
                        enteredBlock = state.value.enteredBlock.copy(
                            subject = event.subject
                        )
                    )
                }
            }
            is TasksEvent.OnEnteredTitle -> {
                _state.update {
                    it.copy(
                        enteredBlock = state.value.enteredBlock.copy(
                            subject = event.title
                        )
                    )
                }
            }
            is TasksEvent.OnUpdateTask -> {
                val tasks = state.value.enteredBlock.tasks.toMutableList()

                tasks[event.index] = event.task

                val block = state.value.enteredBlock.copy(
                    tasks = tasks
                )

                _state.update {
                    it.copy(
                        enteredBlock = block
                    )
                }
            }

            TasksEvent.OnSave -> {
                println("save - ${state.value.enteredBlock}")
            }
        }
    }

}
