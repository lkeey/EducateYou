package dev.lkeeeey.edu.you.profile.students.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class StudentsViewModel (

) : ViewModel() {

    private val _state = MutableStateFlow(StudentsState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()



}
