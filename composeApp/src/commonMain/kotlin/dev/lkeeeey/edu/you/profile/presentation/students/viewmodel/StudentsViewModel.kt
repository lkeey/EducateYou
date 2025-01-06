package dev.lkeeeey.edu.you.profile.presentation.students.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.you.auth.domain.AuthRepository
import dev.lkeeeey.edu.you.profile.domain.ProfileRepository
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

    private val settings = Settings()

    fun onEvent(
        event: StudentsEvent
    ) {
        when (event) {
            StudentsEvent.OnStudentClick -> {

            }

            StudentsEvent.OnLoadStudents -> {
                loadStudents()
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
                .onSuccess {
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
                                    errorMessage = error.name
                                )
                            }
                        }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            errorMessage = error.name
                        )
                    }
                }
        }
    }

}
