package dev.lkeeeey.edu.you.auth.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RegisterViewModel (
//    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onAction(event: RegisterAction) {
        when (event) {
            is RegisterAction.OnConfirmedPasswordChanged -> {
                _state.update {
                    it.copy(
                        confirmedPassword = event.password
                    )
                }

                checkEnabled()
            }
            is RegisterAction.OnLogin -> {
                _state.update {
                    it.copy(
                        event = RegisterEvent.OpenLogin
                    )
                }
            }
            is RegisterAction.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }

                checkEnabled()
            }
            is RegisterAction.OnSignUp -> signup()
            is RegisterAction.OnUsernameChanged -> {
                _state.update {
                    it.copy(
                        username = event.username
                    )
                }

                checkEnabled()
            }

            RegisterAction.ClearEvents -> {
                _state.update {
                    it.copy(
                        event = RegisterEvent.Nothing
                    )
                }
            }
        }
    }

    private fun checkEnabled() {
        if (state.value.password.isNotEmpty() && state.value.username.isNotEmpty() && state.value.confirmedPassword == state.value.password) {
            _state.update {
                it.copy(
                    isButtonEnabled = true,
                    isError = false
                )
            }
        } else {
            _state.update {
                it.copy(
                    isButtonEnabled = false
                )
            }
        }
    }

    private fun signup() {
        _state.update {
            it.copy(
                isLoading = true,
                isButtonEnabled = false
            )
        }

        // sign up
//        viewModelScope.launch {
//            authRepository.registerUser(
//                query = RegisterRequest(
//                    username = state.value.username,
//                    name = state.value.username,
//                    password = state.value.password,
//                    accountType = "student",
//                )
//            )
//            .onSuccess {
//
//                authRepository
//                    .loginUser(
//                        query = LoginRequest(
//                            username = state.value.username,
//                            password = state.value.password,
//                        )
//                    )
//                    .onSuccess { response->
//
//                        authRepository
//                            .updateAccessToken(
//                                access = response.access
//                            )
//                            .onSuccess {
//                                authRepository
//                                    .updateAuthenticated(true)
//                                    .onSuccess {
//
//                                        settings.putString(
//                                            key = Keys.LOGIN,
//                                            value = state.value.username
//                                        )
//
//                                        settings.putString(
//                                            key = Keys.PASSWORD,
//                                            value = state.value.password
//                                        )
//
//                                        _state.update {
//                                            it.copy(
//                                                isLoading = false,
//                                                event = RegisterEvent.OpenMain
//                                            )
//                                        }
//                                    }
//                                    .onError { error->
//                                        _state.update {
//                                            it.copy(
//                                                isLoading = false,
//                                                isError = true,
//                                                errorMessage = error.toStr()
//                                            )
//                                        }
//                                    }
//                            }
//                            .onError { error->
//                                _state.update {
//                                    it.copy(
//                                        isLoading = false,
//                                        isError = true,
//                                        errorMessage = error.toStr()
//                                    )
//                                }
//                            }
//                    }
//                    .onError { error->
//                        _state.update {
//                            it.copy(
//                                isLoading = false,
//                                isError = true,
//                                errorMessage = error.toStr()
//                            )
//                        }
//                    }
//            }
//            .onError { error->
//                _state.update {
//                    it.copy(
//                        isLoading = false,
//                        isError = true,
//                        errorMessage = error.toStr()
//                    )
//                }
//            }
//        }
    }
}