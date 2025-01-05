package dev.lkeeeey.edu.you.auth.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel (
//    private val authRepository: AuthRepository,
//    private val profileRepository: ProfileRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private val settings = Settings()

    init {
        // fetch token
        checkIfUserAuthenticated()
    }

    fun onEvent(
        event: SplashEvent
    ) {
        when (event) {
            SplashEvent.ClearEvents -> {
                _state.update {
                    it.copy(
                        action = SplashAction.Nothing
                    )
                }
            }
        }
    }

    private fun checkIfUserAuthenticated() {
        viewModelScope.launch(Dispatchers.IO) {

            delay(1000)

//            val refresh = settings.getStringOrNull(Keys.REFRESH_TOKEN)
//            val access = settings.getStringOrNull(Keys.ACCESS_TOKEN)
//            val isAuthenticated = settings.getBoolean(Keys.IS_LOGIN, defaultValue = false)


            _state.update {
                it.copy(
                    action = SplashAction.OpenLogin
                )
            }

        }
    }
}
