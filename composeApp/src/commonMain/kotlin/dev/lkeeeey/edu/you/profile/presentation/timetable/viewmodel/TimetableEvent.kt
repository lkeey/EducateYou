package dev.lkeeeey.edu.you.profile.presentation.timetable.viewmodel

sealed interface TimetableEvent {
    data class OnChangeDay(val index : Int): TimetableEvent

    data object OnAddLesson : TimetableEvent
    data object OnSaveDay : TimetableEvent
}