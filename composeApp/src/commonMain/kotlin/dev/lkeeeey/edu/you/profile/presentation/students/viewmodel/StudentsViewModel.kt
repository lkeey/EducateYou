package dev.lkeeeey.edu.you.profile.presentation.students.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.you.auth.domain.AuthRepository
import dev.lkeeeey.edu.you.profile.domain.ProfileRepository
import dev.lkeeeey.edu.you.profile.domain.models.CreateTaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentsViewModel (
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(StudentsState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    fun onEvent(
        event: StudentsEvent
    ) {
        when (event) {
            is StudentsEvent.OnStudentClick -> {
                _state.update {
                    it.copy(
                        chosenStudentUsername = event.username,
                        isLoading = true,
                    )
                }

                loadSubjects()
            }

            StudentsEvent.OnLoadStudents -> {
                loadStudents()
            }

            is StudentsEvent.OnEnterContent -> {
                _state.update {
                    it.copy(
                        enteredContent = event.content
                    )
                }
            }
            is StudentsEvent.OnEnterDeadline -> {
                _state.update {
                    it.copy(
                        enteredDeadline = event.deadline
                    )
                }
            }
            is StudentsEvent.OnEnterExecutionTime -> {
                _state.update {
                    it.copy(
                        enteredTime = event.time
                    )
                }
            }
            is StudentsEvent.OnEnterSubject -> {
                _state.update {
                    it.copy(
                        enteredSubject = event.subject
                    )
                }
            }
            StudentsEvent.OnSave -> {
                _state.update {
                    it.copy(
                        isLoading = true,
                    )
                }

                viewModelScope.launch {
                    authRepository
                        .refreshToken()
                        .onSuccess { res->
                            authRepository.updateAccessToken(res.access)

                            profileRepository
                                .createDistributedTask(
                                    task = CreateTaskModel(
                                        subjectId = state.value.enteredSubject.id,
                                        deadline = state.value.enteredDeadline.toString(),
                                        content = state.value.enteredContent,
                                        executionTime = state.value.enteredTime
                                    ),
                                    username = state.value.chosenStudentUsername
                                )
                                .onSuccess { t->
                                    println("success $t")

                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                        )
                                    }
                                }
                                .onError { e->
                                    println("error $e")
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            errorMessage = e.name,
                                        )
                                    }
                                }
                        }
                        .onError { error ->
                            _state.update {
                                it.copy(
                                    errorMessage = error.name,
                                    isLoading = false
                                )
                            }
                        }
                }
            }
        }
    }

    private fun loadSubjects() {
        viewModelScope.launch {
            authRepository.refreshToken()
                .onSuccess { res ->
                    authRepository.updateAccessToken(res.access)

                    profileRepository
                        .getStudentSubjects(
                            studentUsername = state.value.chosenStudentUsername
                        )
                        .onSuccess { s ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    loadedSubjectsPres = s
                                )
                            }
                        }
                        .onError { e ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = e.name,
                                )
                            }
                        }
                }
                .onError { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.name,
                        )
                    }
                }
        }
    }

    private fun loadStudents() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            authRepository
                .refreshToken()
                .onSuccess { res->
                    authRepository.updateAccessToken(res.access)

                    profileRepository
                        .getStudents()
                        .onSuccess { students->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    students = students
                                )
                            }
                        }
                        .onError { error ->
                            _state.update {
                                it.copy(
                                    errorMessage = error.name,
                                    isLoading = false
                                )
                            }
                        }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            errorMessage = error.name,
                            isLoading = false
                        )
                    }
                }
        }
    }

}
