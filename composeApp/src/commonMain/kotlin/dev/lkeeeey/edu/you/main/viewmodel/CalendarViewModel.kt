package dev.lkeeeey.edu.you.main.viewmodel

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

class CalendarViewModel (
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onEvent(
        event: CalendarEvent
    ) {
        when (event) {
            is CalendarEvent.OnDayClick -> {
                _state.update {
                    it.copy(
                        selectedDate = event.date
                    )
                }
            }

            is CalendarEvent.OnLoadLessons -> {
                loadLessons()
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
}
