package dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.you.auth.domain.AuthRepository
import dev.lkeeeey.edu.you.profile.domain.ProfileRepository
import dev.lkeeeey.edu.you.profile.domain.models.CreateBlockTaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
                            title = event.title
                        )
                    )
                }
            }

            TasksEvent.OnSave -> {
                println("save - ${state.value.enteredBlock}")

                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }

                viewModelScope.launch {
                    authRepository
                        .refreshToken()
                        .onSuccess { res ->
                            authRepository.updateAccessToken(res.access)

                            profileRepository
                                .createBlockOfTasks(
                                    block = state.value.enteredBlock
                                )
                                .onSuccess { t->
                                    println("success $t")

                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            action = TasksAction.OpenProfileTabs
                                        )
                                    }
                                }
                                .onError { error ->
                                    _state.update {
                                        it.copy(
                                            error = error.name,
                                            isLoading = false
                                        )
                                    }
                                }
                        }
                        .onError { error ->
                            _state.update {
                                it.copy(
                                    error = error.name,
                                    isLoading = false
                                )
                            }
                        }
                }
            }

            is TasksEvent.OnUpdateTaskAnswer -> {
                val tasks = state.value.enteredBlock.tasks.toMutableList()

                tasks[event.index] = tasks[event.index].copy(
                    answer = event.answer
                )

                val block = state.value.enteredBlock.copy(
                    tasks = tasks
                )

                _state.update {
                    it.copy(
                        enteredBlock = block
                    )
                }
            }
            is TasksEvent.OnUpdateTaskContent -> {
                val tasks = state.value.enteredBlock.tasks.toMutableList()

                tasks[event.index] = tasks[event.index].copy(
                    content = event.content
                )

                val block = state.value.enteredBlock.copy(
                    tasks = tasks
                )

                _state.update {
                    it.copy(
                        enteredBlock = block
                    )
                }
            }

            TasksEvent.OnClearActions -> {
                _state.update {
                    it.copy(
                        action = TasksAction.Nothing
                    )
                }
            }
        }
    }

}
