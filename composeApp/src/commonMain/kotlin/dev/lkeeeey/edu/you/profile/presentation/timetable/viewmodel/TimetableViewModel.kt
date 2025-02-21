package dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.you.auth.domain.AuthRepository
import dev.lkeeeey.edu.you.main.domain.CreateLessonModel
import dev.lkeeeey.edu.you.profile.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimetableViewModel (
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(TimetableState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    fun onEvent(event: TimetableEvent) {
        when (event) {
            TimetableEvent.OnSaveLesson -> {
                saveLesson(lesson = state.value.enteredLesson)
            }
            is TimetableEvent.OnChangeDay -> {
                _state.update {
                    it.copy(
                        weekDay = event.index
                    )
                }
            }
            is TimetableEvent.OnDeleteLesson -> {
                deleteLesson(id = event.deletedLessonId)
            }
            TimetableEvent.OnLoadLessons -> {
                loadLessons()
            }

            TimetableEvent.OnLoadStudents -> {
                loadStudents()
            }
        }
    }

    private fun saveLesson(lesson: CreateLessonModel) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            authRepository.refreshToken()
                .onSuccess { res ->
                    authRepository.updateAccessToken(res.access)

                    profileRepository
                        .createRelatedLesson(lesson = lesson)
                        .onSuccess {
                            _state.update {
                                it.copy(
                                    enteredLesson = CreateLessonModel()
                                )
                            }

                            loadLessons()
                        }
                        .onError { e ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = e.name,
                                )
                            }
                        }
                }
                .onError { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.name,
                        )
                    }
                }

        }

    }

    private fun deleteLesson(id: Int) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            authRepository.refreshToken()
                .onSuccess { res ->
                    authRepository.updateAccessToken(res.access)

                    profileRepository
                        .deleteRelatedLesson(id = id)
                        .onSuccess {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                )
                            }
                        }
                        .onError { e ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = e.name,
                                )
                            }
                        }
                }
                .onError { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.name,
                        )
                    }
                }

        }
    }

    private fun loadLessons() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            authRepository.refreshToken()
                .onSuccess { res ->
                    authRepository.updateAccessToken(res.access)

                    profileRepository
                        .getTimetable()
                        .onSuccess { l ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    lessons = l
                                )
                            }
                        }
                        .onError { e ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = e.name,
                                )
                            }
                        }
                }
                .onError { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.name,
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
}