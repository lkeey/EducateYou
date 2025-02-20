package dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class TimetableViewModel (

) : ViewModel() {
    private val _state = MutableStateFlow(TimetableState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    fun onEvent(event: TimetableEvent) {
        when (event)
    }
}