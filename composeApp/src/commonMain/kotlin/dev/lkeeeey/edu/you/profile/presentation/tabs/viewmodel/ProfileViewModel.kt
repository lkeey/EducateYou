package dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.you.auth.domain.AuthRepository
import dev.lkeeeey.edu.you.profile.domain.ProfileRepository
import dev.lkeeeey.edu.you.profile.domain.models.UpdateBioModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel (
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnLogOut -> {
                settings.putString(Keys.REFRESH_TOKEN, "")
                settings.putString(Keys.ACCESS_TOKEN, "")

                settings.putString(Keys.LOGIN, "")
                settings.putString(Keys.PASSWORD, "")
                settings.putString(Keys.MY_USERNAME, "")

                settings.putBoolean(Keys.IS_LOGIN, false)
            }

            ProfileEvent.OnOpenProfile -> {
                loadProfile()
            }

            is ProfileEvent.OnBioUpdated -> {
                _state.update {
                    it.copy(
                        enteredBio = event.bio
                    )
                }
            }
            is ProfileEvent.OnSubjectUpdated -> {
                _state.update {
                    it.copy(
                        enteredSubject = event.subject
                    )
                }
            }

            ProfileEvent.OnSave -> {
                updateBio()

//                TODO save subject
            }
        }
    }

    private fun updateBio() {
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
                        .updateTeacherBio(
                            bio = UpdateBioModel(
                                text = state.value.enteredBio
                            )
                        )
                        .onSuccess {
//                            _state.update {
//                                it.copy(
//                                    isLoading = false,
//                                    profile = state.value.profile.copy(
//                                        bio = state.value.enteredBio
//                                    )
//                                )
//                            }

//                            TODO delete
                            loadProfile()
                        }
                        .onError { error->
                            _state.update {
                                it.copy(
                                    errorMessage = error.name,
                                    isLoading = false
                                )
                            }

                        }
                }
                .onError { error->
                    _state.update {
                        it.copy(
                            errorMessage = error.name,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun loadProfile() {
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
                        .getTeacherProfile()
                        .onSuccess { profile ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    profile = profile
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
